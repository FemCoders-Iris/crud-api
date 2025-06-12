package com.femcoders.services;

import com.femcoders.dtos.TopicDTO;
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

    public Topic saveTopic(TopicDTO topicDTO){
        Optional<Topic> isExisting = this.topicRepository.findByName(topicDTO.getName());
        System.out.println("saving TOPIC");
        return isExisting.orElseGet(() -> this.topicRepository.save(TopicDTO.topicDTOToObject(topicDTO)));
    }

    public List<TopicDTO> getTopics() {
        List<Topic> topics = this.topicRepository.findAll();

//        if (phrases.isEmpty()){
//        throw new EmptyListException();
//    }
        return topics.stream()
                .map(topic -> TopicDTO.objectToTopicDTO(topic))
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
