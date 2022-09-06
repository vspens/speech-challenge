package com.project.speech.controller;

import com.project.speech.dto.AuthorDTO;
import com.project.speech.mapper.AuthorMapper;
import com.project.speech.response.ResponseHandler;
import com.project.speech.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Vince Spencer Historia
 * Handles the API for Authors
 */
@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    /**
     * Insert author
     * Example JSON request
     * {
     *     "email" : "vincehistoria@gmail.com",
     *     "name" : "Vince Spencer Historia"
     * }
     * @param authorDTO
     * @return ResponseEntity<Object>
     */
    @PostMapping
    public ResponseEntity<Object> insert(@Valid @RequestBody AuthorDTO authorDTO) {
        AuthorDTO author = authorService.saveAuthor(AuthorMapper.INSTANCE.map(authorDTO));
        if (!Objects.nonNull(author)) {
            return ResponseHandler.generateResponse(authorDTO.getEmail() + " already exist", HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, author);
    }

    /**
     * Get author by ID
     * @param id
     * @return ResponseEntity<Object>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable("id") Long id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        if (!Objects.nonNull(authorDTO)) {
            return ResponseHandler.generateResponse(id + " does not exist", HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, authorDTO);
    }

    /**
     * Get author by email
     * @param email
     * @return ResponseEntity<Object>
     */
    @GetMapping
    public ResponseEntity<Object> getAuthorByEmail(@RequestParam(name = "email") String email) {

        AuthorDTO authorDTO = authorService.findAuthorByEmail(email);
        if (!Objects.nonNull(authorDTO)) {
            return ResponseHandler.generateResponse(email + " does not exist", HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, authorDTO);
    }


}
