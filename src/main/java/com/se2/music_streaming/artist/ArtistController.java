package com.se2.music_streaming.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @PostMapping
    public ResponseEntity<Artist> createNewArtist(@Valid @RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.createArtist(artist), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @Valid @RequestBody Artist artist) {
        return ResponseEntity.ok(artistService.updateArtist(artist, id));
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Artist> getArtist(@PathVariable String identifier) {
        if (identifier.matches("\\d+")) {
            Long id = Long.parseLong(identifier);
            return ResponseEntity.ok(artistService.getArtistById(id));
        } else {
            return ResponseEntity.ok(artistService.getArtistBySlug(identifier));
        }
    }
}
