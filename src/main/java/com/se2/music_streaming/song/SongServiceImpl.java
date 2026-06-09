package com.se2.music_streaming.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.se2.music_streaming.artist.Artist;
import com.se2.music_streaming.artist.ArtistRepository;
import com.se2.music_streaming.exception.DataAlreadyExistsException;
import com.se2.music_streaming.exception.DataConstraintViolationException;
import com.se2.music_streaming.exception.DataNotFoundException;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public Song createSong(Song song, Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new DataNotFoundException("Can't find artist with ID: " + artistId));

        if (songRepository.existsByArtistAndSlug(artist, song.getSlug())) {
            throw new DataConstraintViolationException("This artist has already had a song with this slug!");
        }

        song.setArtist(artist);
        song.setId(null);
        return songRepository.save(song);
    }

    public void deleteSong(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find artist with ID: " + id));

        songRepository.deleteById(song.getId());
    }

    public Song updateSong(Song updatedSong, Long id) {
        Song targetSong = songRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find song with ID: " + id));

        if (!targetSong.getSlug().equals(updatedSong.getSlug())
                && artistRepository.existsBySlug(updatedSong.getSlug())) {
            throw new DataAlreadyExistsException("The new slug has already existed!");
        }
        targetSong.setArtist(updatedSong.getArtist());
        targetSong.setName(updatedSong.getName());
        targetSong.setSlug(updatedSong.getSlug());
        targetSong.setCoverUrl(updatedSong.getCoverUrl());
        targetSong.setFileUrl(updatedSong.getFileUrl());
        targetSong.setLength(updatedSong.getLength());
        targetSong.setGenre(updatedSong.getGenre());

        return songRepository.save(targetSong);
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find song with ID: " + id));
    }

    public Song getSongBySlug(String slug, Long artistId) {
        if (!artistRepository.existsById(artistId)) {
            throw new DataNotFoundException("Can't find artist with ID: " + artistId);
        }
        return songRepository.findBySlug(slug)
                .orElseThrow(() -> new DataNotFoundException("Can't find song with Slug: " + slug));
    }

    public void increaseViewCount(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find song with ID: " + id));

        song.setViewCount(song.getViewCount() + 1);
        songRepository.save(song);
    }

    public Page<Song> getAllSongsFromArtist(Long artistId, Pageable pageable) {
        if (!artistRepository.existsById(artistId)) {
            throw new DataNotFoundException("Can't find artist with ID: " + artistId);
        }

        return songRepository.findByArtistId(artistId, pageable);
    }
}
