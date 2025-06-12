package com.femcoders.specifications;

import com.femcoders.entities.Phrase;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PhraseSpecifications {
    public static Specification<Phrase> filterByParams(String title, String content, String author) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (content != null && !content.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("content")), "%" + content.toLowerCase() + "%"));
            }
//            if (topic != null && !topic.isEmpty()) {
//                predicates.add(cb.like(cb.lower(root.get("topic")), "%" + topic.toLowerCase() + "%"));
//            }
            if (author != null && !author.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

