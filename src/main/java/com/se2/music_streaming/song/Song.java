package com.se2.music_streaming.song;

import java.time.LocalDateTime;

import com.se2.music_streaming.artist.Artist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "song")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true)
    private String slug;

    @Min(1)
    private int length;

    @Builder.Default
    private String genre = "Pop";

    @Builder.Default
    private int viewCount = 0;

    @Column(name = "file_url", length = 500)
    private String fileUrl;

    @Column(name = "cover_url", length = 500)
    private String coverUrl;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
