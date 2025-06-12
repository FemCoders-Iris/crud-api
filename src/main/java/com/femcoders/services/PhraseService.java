package com.femcoders.services;

import com.femcoders.dtos.AuthorDTO;
import com.femcoders.dtos.PhraseDTO;
import com.femcoders.dtos.TopicDTO;
import com.femcoders.entities.Author;
import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;
import com.femcoders.repositories.PhraseRepository;
import com.femcoders.specifications.PhraseSpecifications;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;
    private final TopicService topicService;
    private final AuthorService authorService;

    public PhraseService(PhraseRepository phraseRepository, TopicService topicService, AuthorService authorService) {
        this.phraseRepository = phraseRepository;
        this.topicService = topicService;
        this.authorService = authorService;
    }

//    @Transactional
    public Phrase newPhrase(PhraseDTO phraseDTO) {


        Phrase phrase = PhraseDTO.phraseDTOToObject(phraseDTO);

        phrase.setDateAdded(LocalDateTime.now());
        phrase.setDateModified(LocalDateTime.now());

        AuthorDTO authorDTO = AuthorDTO.objectToAuthorDTO(phrase.getAuthor());
        Author savedAuthor = this.authorService.saveAuthor(authorDTO);
        phrase.setAuthor(savedAuthor);

        List<Topic> managedTopics = new ArrayList<>();
        for (Topic topic: phrase.getTopics()) {
            TopicDTO topicDTO = TopicDTO.objectToTopicDTO(topic);
            Topic savedTopic = this.topicService.saveTopic(topicDTO);
            managedTopics.add(savedTopic);
        }
        phrase.setTopics(managedTopics);

        return this.phraseRepository.save(phrase);
    }

    public Optional<Phrase> getPhraseByContent(String content){
        return this.phraseRepository.findByContent(content);
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

    public Phrase updatePhrase(Integer id, PhraseDTO updatedPhraseDTO){
        Optional<Phrase> phraseOptional = this.phraseRepository.findById(id);

        if(!phraseOptional.isPresent()){
            return null;
        }

        Phrase phrase = phraseOptional.get();

        Phrase newPhrase = PhraseDTO.phraseDTOToObject(updatedPhraseDTO);

        phrase.setDateModified(LocalDateTime.now());

        if(!StringUtils.isEmpty(newPhrase.getTitle())){
            phrase.setTitle(newPhrase.getTitle());
        }

        if(!StringUtils.isEmpty(newPhrase.getContent())){
            phrase.setContent(newPhrase.getContent());
        }


        if(!StringUtils.isEmpty(newPhrase.getAuthor().getName())) {
            AuthorDTO authorDTO = AuthorDTO.objectToAuthorDTO(newPhrase.getAuthor());
            Author savedAuthor = this.authorService.saveAuthor(authorDTO);
            phrase.setAuthor(savedAuthor);
        }

        List<Topic> managedTopics = new ArrayList<>();
        if(newPhrase.getTopics() != null && !newPhrase.getTopics().isEmpty()) {
            for (Topic topic : newPhrase.getTopics()) {
                TopicDTO topicDTO = TopicDTO.objectToTopicDTO(topic);
                Topic savedTopic = this.topicService.saveTopic(topicDTO);
                managedTopics.add(savedTopic);
            }
        }
        phrase.setTopics(managedTopics);

        return this.phraseRepository.save(phrase);
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
