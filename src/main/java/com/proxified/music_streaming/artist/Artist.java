package com.proxified.music_streaming.artist;

import java.util.Set;

import com.proxified.music_streaming.song.Song;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String artistName;
    private String bio;
    private String avatarUrl;

    @OneToMany
    private Set<Song> songs;
}
