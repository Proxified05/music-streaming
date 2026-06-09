package com.se2.music_streaming.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    SongService songService;

    @PostMapping
    public ResponseEntity<Song> createNewSong(@Valid @RequestBody Song song, @RequestParam Long artistId) {
        return new ResponseEntity<>(songService.createSong(song, artistId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @Valid @RequestBody Song song) {
        return ResponseEntity.ok(songService.updateSong(song, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));

    }

    @GetMapping("/{artistId}/{slug}")
    public ResponseEntity<Song> getSongBySlug(@PathVariable String slug, @PathVariable Long artistId) {
        return ResponseEntity.ok(songService.getSongBySlug(slug, artistId));
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Page<Song>> getAllSongsFromArtist(@PathVariable Long artistId, Pageable pageable) {
        return ResponseEntity.ok(songService.getAllSongsFromArtist(artistId, pageable));
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Void> playSong(@PathVariable Long id) {
        songService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }
}
