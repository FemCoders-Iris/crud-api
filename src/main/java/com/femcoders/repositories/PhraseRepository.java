package com.femcoders.repositories;

import com.femcoders.entities.Phrase;
import com.femcoders.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Integer>, JpaSpecificationExecutor<Phrase> {

    @Query(value = "SELECT p.* FROM phrases p " +
            "LEFT JOIN phrase_topic pt ON p.id = pt.id " +
            "LEFT JOIN topics t ON pt.id_topic = t.id_topic " +
            "LEFT JOIN authors a ON a.id = p.author_id " +
            "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(a.name) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(t.name) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "GROUP BY p.id",
            nativeQuery = true)
    List<Phrase> searchPhrases(@Param("searchText") String searchText);

    Optional<Phrase> findByContent(String name);


}
