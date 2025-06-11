package com.femcoders.repositories;

import com.femcoders.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Topic save(Topic topic);

    Optional<Topic> findByName(String name);
}