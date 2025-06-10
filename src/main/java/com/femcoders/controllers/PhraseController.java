package com.femcoders.controllers;

import com.femcoders.services.PhraseService;
import com.femcoders.models.Phrase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phrases")
public class PhraseController {
    private final PhraseService phraseService;

    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPhrase(@RequestBody Phrase phrase){
        return this.phraseService.newPhrase(phrase);
    }

    @GetMapping("/")
    public ResponseEntity<List<Phrase>> getPhrases(){
        List<Phrase> phrases = this.phraseService.getPhrases();
        return new ResponseEntity<>(phrases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPhraseById(@PathVariable Integer id ){
        Phrase phrase = this.phraseService.getPhraseById(id);

        if (phrase == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(phrase);
    }
}
