package com.femcoders.crud_api;

import com.femcoders.repository.PhraseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;

    public PhraseService(PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    public ResponseEntity<Object> newPhrase(Phrase phrase) {
        phraseRepository.save(phrase);
        return new ResponseEntity<>(phrase, HttpStatus.CREATED);
    }
}
