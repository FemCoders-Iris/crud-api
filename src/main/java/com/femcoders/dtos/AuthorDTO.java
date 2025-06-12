package com.femcoders.dtos;

import com.femcoders.entities.Phrase;
import com.femcoders.entities.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private int id;
    private String name;
    private List<String> phrases;
    
    public AuthorDTO(){}

    public static Author authorDTOToObject(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        return author;
    }

    public static AuthorDTO objectToAuthorDTO(Author author){
        List<String> phrasesInDTO = new ArrayList<>();
        AuthorDTO authorDTO = new AuthorDTO();
        if(author.getPhrases()!= null){
            for(Phrase phrase: author.getPhrases()){
                phrasesInDTO.add(phrase.getContent());
            }
                authorDTO.setPhrases(phrasesInDTO);
        }

        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());

        return authorDTO;
    }

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

    public List<String> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }
}
