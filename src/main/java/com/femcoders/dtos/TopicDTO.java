package com.femcoders.dtos;

import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicDTO {
    private int id_topic;
    private String name;
    private List<PhraseDTOShort> phrases;

    public TopicDTO(){}

    public TopicDTO(int id_topic, String name) {
        this.id_topic = id_topic;
        this.name = name;
    }

    public TopicDTO(int id_topic, String name, List<PhraseDTOShort> phrases) {
        this.id_topic = id_topic;
        this.name = name;
        this.phrases = phrases;
    }

    public static TopicDTO topicDTOComplete(Topic topic){
        List<PhraseDTOShort> phrases = new ArrayList<>();
        for(Phrase phrase: topic.getPhrasesInTopic()){
            List<String> topics = new ArrayList<>();
            for(Topic topicInPhrase: phrase.getTopics()){
                topics.add(topicInPhrase.getName());
            }
            PhraseDTOShort phraseDTO = new PhraseDTOShort(phrase.getId(), phrase.getTitle(), phrase.getContent(), topics);
            phrases.add(phraseDTO);
        }
        TopicDTO topicDTO = new TopicDTO(topic.getId_topic(), topic.getName(), phrases);
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

    public List<PhraseDTOShort> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<PhraseDTOShort> phrases) {
        this.phrases = phrases;
    }
}
