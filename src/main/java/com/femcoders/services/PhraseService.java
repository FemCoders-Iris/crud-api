package com.femcoders.services;

import com.femcoders.dtos.PhraseDTO;
import com.femcoders.dtos.TopicDTO;
import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;
import com.femcoders.repositories.PhraseRepository;
import com.femcoders.specifications.PhraseSpecifications;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;
    private final TopicService topicService;

    public PhraseService(PhraseRepository phraseRepository, TopicService topicService) {
        this.phraseRepository = phraseRepository;
        this.topicService = topicService;
    }

    @Transactional
    public Phrase newPhrase(PhraseDTO phraseDTO) {
        Optional<Phrase> isExisting = this.phraseRepository.findByContent(phraseDTO.getContent());
        if(isExisting.isPresent()){
            return isExisting.get();
        }

        Phrase phrase = PhraseDTO.phraseDTOToObject(phraseDTO);

        phrase.setDateAdded(LocalDateTime.now());
        phrase.setDateModified(LocalDateTime.now());

        List<Topic> managedTopics = new ArrayList<>();
        for (Topic topic: phrase.getTopics()) {
            TopicDTO topicDTO = TopicDTO.objectToTopicDTO(topic);
            Topic savedTopic = this.topicService.saveTopic(topicDTO);
            managedTopics.add(savedTopic);
        }
        phrase.setTopics(managedTopics);

        return this.phraseRepository.save(phrase);
    }


    public List<Phrase> getPhrases(){
        return this.phraseRepository.findAll();
    }

    public Phrase getPhraseById(Integer id){
        Optional<Phrase> phraseOptional = this.phraseRepository.findById(id);

        if(!phraseOptional.isPresent()){
            return null;
        }

        return phraseOptional.get();
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
//        if(!StringUtils.isEmpty(updatedPhrase.getTopic())){
//            existingPhrase.setTopic(updatedPhrase.getTopic());
//        }

        existingPhrase.setDateModified(LocalDateTime.now());
        this.phraseRepository.save(existingPhrase);
        return existingPhrase;
    }

    public List<Phrase> searchPhrases(String searchText) {
        return  this.phraseRepository.searchPhrases(searchText);
    }

    public List<Phrase> getAllOrderBy (String category, boolean direction){
        if(!direction){
            return this.phraseRepository.findAll(Sort.by(Sort.Direction.DESC, category));
        }
        return this.phraseRepository.findAll(Sort.by(category));
    }

    public List<Phrase> getAllFilter(String title, String content, String author) {
        Specification<Phrase> spec = PhraseSpecifications.filterByParams(title, content, author);
        return phraseRepository.findAll(spec);
    }

}
