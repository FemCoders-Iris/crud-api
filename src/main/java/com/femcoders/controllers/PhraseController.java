package com.femcoders.controllers;

import com.femcoders.services.PhraseService;
import com.femcoders.models.Phrase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/phrases")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePhraseById(@PathVariable Integer id ){
        Phrase phrase = this.phraseService.deletePhraseById(id);

        if (phrase == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePhraseById(@PathVariable Integer id, @RequestBody Phrase updatedPhrase ){
        Phrase phrase = this.phraseService.updatePhrase(id, updatedPhrase);

        if (phrase == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(phrase);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Phrase>> searchPhrases(@RequestParam String searchText) {
        List<Phrase> foundPhrases = this.phraseService.searchPhrases(searchText);
        if (!foundPhrases.isEmpty()) {
            return ResponseEntity.ok(foundPhrases);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<Phrase>> getPhrasesOrderBy(
            @RequestParam(required=false, defaultValue ="id")String orderCategory,
            @RequestParam(required=false, defaultValue="true")boolean orderDirection){
        List<Phrase> phrases = this.phraseService.getAllOrderBy(orderCategory, orderDirection);
        return new ResponseEntity<>(phrases, HttpStatus.OK);
    }
}
