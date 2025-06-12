package com.femcoders.services;

import com.femcoders.dtos.AuthorDTO;
import com.femcoders.entities.Author;
import com.femcoders.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(AuthorDTO authorDTO){
        Optional<Author> isExisting = this.authorRepository.findByName(authorDTO.getName());
        System.out.println("saving AUTHOR");
        return isExisting.orElseGet(() -> this.authorRepository.save(AuthorDTO.authorDTOToObject(authorDTO)));
    }
}
