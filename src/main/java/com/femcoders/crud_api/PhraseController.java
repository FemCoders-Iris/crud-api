package com.femcoders.crud_api;

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
