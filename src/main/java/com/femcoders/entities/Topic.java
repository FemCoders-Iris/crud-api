package com.femcoders.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id_topic")
@Entity
@Table(name = "topics")
public class Topic {
//    @JsonView({PhraseView.class, TopicView.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_topic;

//    @JsonView({PhraseView.class, TopicView.class})
    @NotNull
    private String name;


//    @JsonView({TopicView.class})
//    @JsonBackReference
    @ManyToMany(mappedBy = "topics")
    List<Phrase> phrasesInTopic = new ArrayList<>();

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

    public List<Phrase> getPhrasesInTopic() {
        return phrasesInTopic;
    }

    public void setPhrasesInTopic(List<Phrase> phrasesInTopic) {
        this.phrasesInTopic = phrasesInTopic;
    }
}
