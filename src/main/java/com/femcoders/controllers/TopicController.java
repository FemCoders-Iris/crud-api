package com.femcoders.controllers;

import com.femcoders.dtos.TopicDTO;
import com.femcoders.services.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

//    @JsonView(TopicView.class)
    @GetMapping("/")
    public ResponseEntity<List<TopicDTO>> getTopics(){
        List<TopicDTO> topics = this.topicService.getTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }
}
