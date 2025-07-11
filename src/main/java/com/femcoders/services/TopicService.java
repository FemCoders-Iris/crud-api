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

    public Topic updateTopic(Integer id, TopicDTO topicDTO){
        Optional<Topic> isExisting = this.topicRepository.findById(id);
        Topic newTopic = isExisting.get();
        newTopic.setName(topicDTO.getName());
        return newTopic;
    }

    public Optional<Topic> findById(Integer id){
        return this.topicRepository.findById(id);
    }

    public Optional<Topic> findByName(String name){
        return this.topicRepository.findByName(name);
    }

    public Topic newTopic(TopicDTO topicDTO){
        return this.topicRepository.save(TopicDTO.topicDTOToObject(topicDTO));
    }
}
