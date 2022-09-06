package com.project.speech.service;

import com.project.speech.entity.Author;
import com.project.speech.entity.Speech;
import com.project.speech.repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;

    @Before
    public void setUp() {
        author = new Author();
        author.setEmail("test@email.com");
        author.setName("John Doe");
        author.setId(123L);
    }

    @Test
    public void test_getAuthorById_authorNotNull() {
        Mockito.when(authorRepository.findAuthorById(Mockito.anyLong())).thenReturn(author);

        assertEquals(authorService.getAuthorById(123L).getEmail(), "test@email.com");
    }

    @Test
    public void test_saveAuthor_() {
        Mockito.when(authorRepository.findAuthorByEmail("test@email.com")).thenReturn(null);
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);
        assertEquals(authorService.saveAuthor(author).getEmail(), "test@email.com");
    }

    @Test
    public void test_findAuthorByEmail() {
        Mockito.when(authorRepository.findAuthorByEmail(Mockito.anyString())).thenReturn(author);
        assertEquals(authorService.findAuthorByEmail("test@email.com").getEmail(), "test@email.com");
    }
}
