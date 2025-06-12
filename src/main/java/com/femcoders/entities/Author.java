package com.femcoders.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Phrase> phrases;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }
}
