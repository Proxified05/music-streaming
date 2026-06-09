package com.se2.music_streaming.artist;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se2.music_streaming.song.Song;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "artist")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(unique = true)
    private String slug;

    @Size(max = 2000)
    private String bio;

    @Size(max = 500)
    private String avatarUrl;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Song> songs;
}
