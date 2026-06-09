package com.se2.music_streaming.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se2.music_streaming.exception.DataAlreadyExistsException;
import com.se2.music_streaming.exception.DataConstraintViolationException;
import com.se2.music_streaming.exception.DataNotFoundException;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public Artist createArtist(Artist artist) {
        if (artistRepository.existsBySlug(artist.getSlug())) {
            throw new DataAlreadyExistsException("There's already an artist with this slug");
        }
        artist.setId(null);
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        Artist targetArtist = artistRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("There's no artist with id: " + id));
        if (targetArtist.getSongs() != null && !targetArtist.getSongs().isEmpty()) {
            throw new DataConstraintViolationException(
                    "Artist can't be deleted because the artist has published songs on the site!");
        }
        artistRepository.deleteById(targetArtist.getId());
    }

    public Artist updateArtist(Artist updatedArtist, Long id) {
        Artist targetArtist = artistRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("There's no artist with id: " + id));

        if (!targetArtist.getSlug().equals(updatedArtist.getSlug())
                && artistRepository.existsBySlug(updatedArtist.getSlug())) {
            throw new DataAlreadyExistsException("The new slug has already existed!");
        }

        targetArtist.setName(updatedArtist.getName());
        targetArtist.setSlug(updatedArtist.getSlug());
        targetArtist.setBio(updatedArtist.getBio());
        targetArtist.setAvatarUrl(updatedArtist.getAvatarUrl());
        return artistRepository.save(targetArtist);
    }

    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("There's no artist with id: " + id));
    }

    public Artist getArtistBySlug(String slug) {
        return artistRepository.findBySlug(slug)
                .orElseThrow(() -> new DataNotFoundException("There's no artist with slug: " + slug));
    }
}
