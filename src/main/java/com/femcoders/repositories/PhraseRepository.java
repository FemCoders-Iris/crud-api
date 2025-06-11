package com.femcoders.repositories;

import com.femcoders.entities.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Integer>, JpaSpecificationExecutor<Phrase> {
//    @Query("SELECT p FROM Phrase p JOIN p.topics t WHERE " +
//            "LOWER(p.title) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(p.content) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(p.author) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
//            "LOWER(t.name) LIKE LOWER(CONCAT('%', :searchText, '%'))")
//    List<Phrase> searchPhrases(@Param("searchText") String searchText);
    @Query(value = "SELECT p.* FROM phrases p " +
            "LEFT JOIN phrase_topic pt ON p.id = pt.id " +
            "LEFT JOIN topics t ON pt.id_topic = t.id_topic " +
            "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(p.author) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(t.name) LIKE LOWER(CONCAT('%', :searchText, '%'))",
            nativeQuery = true)
    List<Phrase> searchPhrases(@Param("searchText") String searchText);


}
