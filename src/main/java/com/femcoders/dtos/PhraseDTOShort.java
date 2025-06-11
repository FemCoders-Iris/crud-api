package com.femcoders.dtos;

import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class PhraseDTOShort {
    private int id;
    private String title;
    private String content;
    private List<String> topics;

    public PhraseDTOShort() {
    }

    public PhraseDTOShort(int id, String title, String content, List<String> topics) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.topics = topics;
    }

    public PhraseDTOShort(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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
}
