package com.femcoders.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "phrases")
public class Phrase {
    @JsonView({PhraseView.class, TopicView.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView({PhraseView.class, TopicView.class})
    private String title;

    @JsonView({PhraseView.class, TopicView.class})
    @NotNull
    private String content;

    @JsonView({PhraseView.class, TopicView.class})
    private String author;

    @JsonView({PhraseView.class, TopicView.class})
    private LocalDateTime dateAdded;

    @JsonView({PhraseView.class, TopicView.class})
    private LocalDateTime dateModified;

    @ManyToMany
    @JsonView({PhraseView.class})
    @JoinTable(
    name="phrase_topic",
    joinColumns = @JoinColumn(name="id"),
    inverseJoinColumns = @JoinColumn(name="id_topic"))
    Set<Topic> topicsInPhrase = new HashSet<>();

    public Phrase() {}

    public Phrase(String title, String content, String author, Set<Topic> topicsInPhrase) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.topicsInPhrase = topicsInPhrase;
    }


    public void addTopic(Topic topic){
        this.topicsInPhrase.add(topic);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Topic> getTopicsInPhrase() {
        return topicsInPhrase;
    }

    public void setTopicsInPhrase(Set<Topic> topicsInPhrase) {
        this.topicsInPhrase = topicsInPhrase;
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
