package com.se2.music_streaming.artist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findBySlug(String slug);

    boolean existsBySlug(String slug);
}
