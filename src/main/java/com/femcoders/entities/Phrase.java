package com.femcoders.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "phrases")
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    private LocalDateTime dateAdded;

    private LocalDateTime dateModified;

    @ManyToMany
    @JoinTable(
    name="phrase_topic",
    joinColumns = @JoinColumn(name="id"),
    inverseJoinColumns = @JoinColumn(name="id_topic"))
    List<Topic> topics = new ArrayList<>();

    public Phrase() {}

    public Phrase(String title, String content, Author author, List<Topic> topics) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.topics = topics;
    }

    public void addTopic(Topic topic){
        this.topics.add(topic);
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
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
