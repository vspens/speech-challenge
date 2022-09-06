package com.project.speech.service;

import com.project.speech.dto.AuthorDTO;
import com.project.speech.entity.Author;
import com.project.speech.mapper.AuthorMapper;
import com.project.speech.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Vince Spencer Historia
 * Service class for Author
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get Author by ID
     * @param id
     * @return AuthorDTO
     */
    public AuthorDTO getAuthorById(Long id) {
        return AuthorMapper.INSTANCE.map(authorRepository.findAuthorById(id));
    }

    /**
     * Save author
     * @param author
     * @return AuthorDTO
     */
    public AuthorDTO saveAuthor(Author author) {
        Author authorResult = null;
        if (authorRepository.findAuthorByEmail(author.getEmail()) == null) {
            authorResult = authorRepository.save(author);
        }
        return AuthorMapper.INSTANCE.map(authorResult);
    }

    /**
     * Get author by email
     * @param email
     * @return AuthorDTO
     */
    public AuthorDTO findAuthorByEmail(String email) {
        return AuthorMapper.INSTANCE.map(authorRepository.findAuthorByEmail(email));
    }
}
