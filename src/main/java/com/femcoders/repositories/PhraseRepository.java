package com.femcoders.repositories;

import com.femcoders.models.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhraseRepository extends JpaRepository<Phrase, Integer> {
    @Query("SELECT * FROM phrases p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(p.content) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(p.topic) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(p.author) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Phrase> findPhrasesBySearchText(@Param("searchText") String searchText);
}
