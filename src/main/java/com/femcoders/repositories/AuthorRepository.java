package com.femcoders.repositories;

import com.femcoders.entities.Author;
import com.femcoders.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByName(String name);
}
