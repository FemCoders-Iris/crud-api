package com.femcoders.controllers;

import com.femcoders.services.PhraseService;
import com.femcoders.models.Phrase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phrases")
public class PhraseController {
    private final PhraseService phraseService;

    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPhrase(@RequestBody Phrase phrase){
        return phraseService.newPhrase(phrase);
    }
}
