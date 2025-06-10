package com.femcoders.services;

import com.femcoders.models.Phrase;
import com.femcoders.repositories.PhraseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;

    public PhraseService(PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    public ResponseEntity<Object> newPhrase(Phrase phrase) {
        this.phraseRepository.save(phrase);
        return new ResponseEntity<>(phrase, HttpStatus.CREATED);
    }

    public List<Phrase> getPhrases(){
        return this.phraseRepository.findAll();
    }

    public ResponseEntity<Object> getPhraseById(Integer id){
        Optional<Phrase> phraseOptional = this.phraseRepository.findById(id);

        if(!phraseOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Phrase phrase = phraseOptional.get();

        return ResponseEntity.ok(phrase);
    }
}
