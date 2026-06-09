package com.se2.music_streaming.artist;

public interface ArtistService {
    Artist createArtist(Artist artist);

    void deleteArtist(Long id);

    Artist updateArtist(Artist artist, Long id);

    Artist getArtistById(Long id);

    Artist getArtistBySlug(String slug);

    // Thêm các search function sau
}
