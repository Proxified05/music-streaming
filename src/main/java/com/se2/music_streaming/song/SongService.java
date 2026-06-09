package com.se2.music_streaming.song;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface SongService {
    Song createSong(Song song, Long artistId);

    void deleteSong(Long id);

    Song updateSong(Song song, Long id);

    Song getSongById(Long id);

    Song getSongBySlug(String slug, Long artistId);

    void increaseViewCount(Long id);

    Page<Song> getAllSongsFromArtist(Long id, Pageable pageable);

    // Thêm các search function sau
}
