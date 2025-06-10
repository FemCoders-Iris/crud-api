package com.femcoders.services;

import com.femcoders.models.Phrase;
import com.femcoders.repositories.PhraseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;

    public PhraseService(PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    public ResponseEntity<Object> newPhrase(Phrase phrase) {
        phrase.setDateAdded(LocalDateTime.now());
        phrase.setDateModified(LocalDateTime.now());
        this.phraseRepository.save(phrase);
        return new ResponseEntity<>(phrase, HttpStatus.CREATED);
    }

    public List<Phrase> getPhrases(){
        return this.phraseRepository.findAll();
    }

    public Phrase getPhraseById(Integer id){
        Optional<Phrase> phraseOptional = this.phraseRepository.findById(id);

        if(!phraseOptional.isPresent()){
            return null;
        }

        Phrase phrase = phraseOptional.get();

        return phrase;
    }

    public Phrase deletePhraseById(Integer id){
        Optional<Phrase> phraseOptional = this.phraseRepository.findById(id);

        if(!phraseOptional.isPresent()){
            return null;
        }

        Phrase phrase = phraseOptional.get();
        this.phraseRepository.deleteById(id);

        return phrase;
    }

    public Phrase updatePhrase(Integer id, Phrase updatedPhrase){
        Optional<Phrase> phraseOptional = this.phraseRepository.findById(id);

        //validate number of characters
        if(!phraseOptional.isPresent()){
            return null;
        }

        Phrase existingPhrase = phraseOptional.get();
        if(!StringUtils.isEmpty(updatedPhrase.getAuthor())){
            existingPhrase.setAuthor(updatedPhrase.getAuthor());
        }
        if(!StringUtils.isEmpty(updatedPhrase.getContent())){
            existingPhrase.setContent(updatedPhrase.getContent());
        }
        if(!StringUtils.isEmpty(updatedPhrase.getTitle())){
            existingPhrase.setTitle(updatedPhrase.getTitle());
        }
        if(!StringUtils.isEmpty(updatedPhrase.getTopic())){
            existingPhrase.setTopic(updatedPhrase.getTopic());
        }

        existingPhrase.setDateModified(LocalDateTime.now());
        this.phraseRepository.save(existingPhrase);
        return existingPhrase;
    }

    public List<Phrase> searchPhrases(String searchText) {
        return  this.phraseRepository.findPhrasesBySearchText(searchText);
    }
}
