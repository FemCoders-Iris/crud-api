package com.femcoders.dtos;

import com.femcoders.entities.Author;
import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PhraseDTO {
    private int id;
    private String title;
    private String content;
    private String author;
    private List<String> topics;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;

    public PhraseDTO() {
    }

    public static Phrase phraseDTOToObject(PhraseDTO phraseDTO) {
        Phrase phrase = new Phrase();
        phrase.setTitle(phraseDTO.getTitle());
        phrase.setContent(phraseDTO.getContent());
        Author author = new Author();
        author.setName(phraseDTO.getAuthor());
        phrase.setAuthor(author);
        phrase.setDateAdded(phraseDTO.getDateAdded());
        List<Topic> topics = new ArrayList<>();
        for(String topicName: phraseDTO.getTopics()){
            System.out.println(topicName);
            Topic topic = new Topic();
            topic.setName(topicName);
            topics.add(topic);
        }
        phrase.setTopics(topics);
        return phrase;
    }

    public static PhraseDTO objectToPhraseDTO(Phrase phrase) {
        PhraseDTO phraseDTO = new PhraseDTO();
        phraseDTO.setId(phrase.getId());
        phraseDTO.setTitle(phrase.getTitle());
        phraseDTO.setContent(phrase.getContent());
        phraseDTO.setAuthor(phrase.getAuthor().getName());
        phraseDTO.setDateAdded(phrase.getDateAdded());
        phraseDTO.setDateModified(phrase.getDateModified());
        List<String> topicsName = new ArrayList<>();
        for(Topic topic: phrase.getTopics()){
            topicsName.add(topic.getName());
        }
        phraseDTO.setTopics(topicsName);
        return phraseDTO;
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

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }
}
