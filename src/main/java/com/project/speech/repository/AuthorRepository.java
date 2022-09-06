package com.project.speech.repository;

import com.project.speech.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Vince Spencer Historia
 * Author Repository
 */
@Component
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Get author by ID
     * @param id
     * @return Author
     */
    Author findAuthorById(Long id);

    /**
     * Get author by email
     * @param email
     * @return Author
     */
    Author findAuthorByEmail(String email);
}
