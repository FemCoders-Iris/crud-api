package com.femcoders.services;

import com.femcoders.dtos.PhraseDTO;
import com.femcoders.dtos.TopicDTO;
import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;
import com.femcoders.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    private final TopicRepository topicRepository;


    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic saveTopic(Topic topic){
        Optional<Topic> isExisting = this.topicRepository.findByName(topic.getName());

        if (isExisting.isPresent()){
            return isExisting.get();
        }
        Topic savedTopic = new Topic();
        savedTopic.setName(topic.getName());
        return this.topicRepository.save(savedTopic);

    }

    public List<TopicDTO> getTopics() {
        List<Topic> topics = this.topicRepository.findAll();

//        if (phrases.isEmpty()){
//        throw new EmptyListException();
//    }
        return topics.stream()
                .map(topic -> TopicDTO.topicDTOComplete(topic))
                .toList();
    }
//    public  Topic findByName(String name){
//        Optional<Topic> isExisting = this.topicRepository.findByName(name);
//
//        if (isExisting.isPresent()){
//            return isExisting.get();
//        }
//        Topic savedTopic = new Topic(name);
//        this.topicRepository.save(savedTopic);
//        return savedTopic;
//    }
}
