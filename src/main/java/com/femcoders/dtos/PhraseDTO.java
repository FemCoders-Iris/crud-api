package com.femcoders.dtos;

import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class PhraseDTO {
    private int id;
    private String title;
    private String content;
    private List<TopicDTO> topics;

    public PhraseDTO() {
    }

    public PhraseDTO(int id, String title, String content, List<TopicDTO> topics) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.topics = topics;
    }

    public PhraseDTO(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PhraseDTO phraseDTOComplete(Phrase phrase) {
        List<TopicDTO> topics = new ArrayList<>();
        for(Topic topic: phrase.getTopics()){
            TopicDTO topicDTO = new TopicDTO(topic.getId_topic(), topic.getName());
            topics.add(topicDTO);
        }
        return new PhraseDTO(phrase.getId(), phrase.getTitle(), phrase.getContent(), topics);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }
}
