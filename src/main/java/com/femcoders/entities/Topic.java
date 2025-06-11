package com.femcoders.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic {
    @JsonView({PhraseView.class, TopicView.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_topic;

    @JsonView({PhraseView.class, TopicView.class})
    @NotNull
    private String name;

    @JsonView({TopicView.class})
    @ManyToMany(mappedBy = "topicsInPhrase")
//    @JsonBackReference
    Set<Phrase> phrasesInTopic = new HashSet<>();

    public Topic() {

    }

    public Topic(String name) {
        this.name = name;
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

    public Set<Phrase> getPhrasesInTopic() {
        return phrasesInTopic;
    }

    public void setPhrasesInTopic(Set<Phrase> phrasesInTopic) {
        this.phrasesInTopic = phrasesInTopic;
    }
}
