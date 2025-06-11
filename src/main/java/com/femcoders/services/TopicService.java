package com.femcoders.services;

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

    public List<Topic> getTopics(){
        return this.topicRepository.findAll();
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
