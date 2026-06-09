package com.se2.music_streaming.song;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.se2.music_streaming.artist.Artist;

public interface SongRepository extends JpaRepository<Song, Long> {
    boolean existsBySlug(String slug);

    boolean existsByArtistAndSlug(Artist artist, String slug);

    Optional<Song> findBySlugAndArtistSlug(String songSlug, String artistSlug);

    Optional<Song> findBySlug(String slug);

    Page<Song> findByArtistId(Long artistId, Pageable pageable);
}