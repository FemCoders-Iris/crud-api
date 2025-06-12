package com.femcoders.controllers;

import com.femcoders.dtos.TopicDTO;
import com.femcoders.entities.Topic;
import com.femcoders.services.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<TopicDTO>> getTopics(){
        List<TopicDTO> topics = this.topicService.getTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }
    
    @PostMapping(path="/")
    public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topicDTO){
        Optional<Topic> isExisting = this.topicService.findByName(topicDTO.getName());
        if(isExisting.isPresent()){
            return new ResponseEntity<>(topicToDTO(isExisting.get()), HttpStatus.SEE_OTHER);
        }
        Topic topic = this.topicService.newTopic(topicDTO);
        return new ResponseEntity<>(topicToDTO(topic), HttpStatus.CREATED);
    }

    public TopicDTO topicToDTO(Topic topic){
        return TopicDTO.objectToTopicDTO(topic);
    }

    public List<TopicDTO> listTopicToDTO(List<Topic> topics){
        return topics.stream()
                .map(topic -> TopicDTO.objectToTopicDTO(topic))
                .toList();
    }
}
