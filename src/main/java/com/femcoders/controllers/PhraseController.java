package com.femcoders.controllers;

import com.femcoders.dtos.PhraseDTO;
import com.femcoders.services.PhraseService;
import com.femcoders.entities.Phrase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/phrases")
public class PhraseController {
    private final PhraseService phraseService;

    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @PostMapping(path="/")
    public ResponseEntity<PhraseDTO> createPhrase(@RequestBody PhraseDTO phraseDTO){
        Optional<Phrase> isExisting = this.phraseService.getPhraseByContent(phraseDTO.getContent());
        if(isExisting.isPresent()){
            return new ResponseEntity<>(phraseToDTO(isExisting.get()), HttpStatus.SEE_OTHER);
        }
        Phrase phrase = this.phraseService.newPhrase(phraseDTO);
        return new ResponseEntity<>(phraseToDTO(phrase), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PhraseDTO>> getPhrases(){
        List<Phrase> phrases = this.phraseService.getPhrases();
        return new ResponseEntity<>(listPhraseToDTO(phrases), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhraseDTO> getPhraseById(@PathVariable Integer id ){
        Phrase phrase = this.phraseService.getPhraseById(id);

        if (phrase == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(phraseToDTO(phrase));
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
    public ResponseEntity<PhraseDTO> updatePhraseById(@PathVariable Integer id, @RequestBody PhraseDTO updatedPhrase ){
        Phrase phrase = this.phraseService.updatePhrase(id, updatedPhrase);

        if (phrase == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(phraseToDTO(phrase));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PhraseDTO>> searchPhrases(@RequestParam String searchText) {
        List<Phrase> foundPhrases = this.phraseService.searchPhrases(searchText);
        if (!foundPhrases.isEmpty()) {
            return ResponseEntity.ok(listPhraseToDTO(foundPhrases));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<PhraseDTO>> getPhrasesOrderBy(
            @RequestParam(required=false, defaultValue ="id")String orderCategory,
            @RequestParam(required=false, defaultValue="true")boolean orderDirection){
        List<Phrase> phrases = this.phraseService.getAllOrderBy(orderCategory, orderDirection);
        return new ResponseEntity<>(listPhraseToDTO(phrases), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PhraseDTO>> getPhrasesFilter(
            @RequestParam(required=false)String title,
            @RequestParam(required=false)String content,
            @RequestParam(required=false)String author
    ){
        List<Phrase> phrases = this.phraseService.getAllFilter(title, content, author);
        if(phrases.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(listPhraseToDTO(phrases), HttpStatus.OK);
    }

    public PhraseDTO phraseToDTO(Phrase phrase){
        return PhraseDTO.objectToPhraseDTO(phrase);
    }

    public List<PhraseDTO> listPhraseToDTO(List<Phrase> phrases){
        return phrases.stream()
                .map(phrase -> PhraseDTO.objectToPhraseDTO(phrase))
                .toList();
    }
}
