package com.femcoders.dtos;

import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicDTO {
    private int id_topic;
    private String name;
    private List<String> phrases;

    public TopicDTO(){

    }

    public static Topic topicDTOToObject(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setName(topicDTO.getName());
        return topic;
    }

    public static TopicDTO objectToTopicDTO(Topic topic){
        List<String> phrasesInDTO = new ArrayList<>();
        for(Phrase phrase: topic.getPhrasesInTopic()){
            phrasesInDTO.add(phrase.getContent());
        }
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId_topic(topic.getId_topic());
        topicDTO.setName(topic.getName());
        topicDTO.setPhrases(phrasesInDTO);
        return topicDTO;
    }

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }
}
