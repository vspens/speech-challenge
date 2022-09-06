package com.project.speech.controller;

import com.project.speech.dto.AuthorDTO;
import com.project.speech.entity.Author;
import com.project.speech.mapper.AuthorMapper;
import com.project.speech.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerTest {

    @InjectMocks
    private AuthorController controller;

    @Mock
    private AuthorService service;

    private Author author;

    private AuthorDTO authorDTO;

    @Before
    public void setUp() {
        author = new Author();
        author.setEmail("test@email.com");
        author.setName("John Doe");
        author.setId(123L);
    }

    @Test
    public void test_insert() {
        authorDTO = AuthorMapper.INSTANCE.map(author);

        Mockito.when(service.saveAuthor(Mockito.any(Author.class))).thenReturn(authorDTO);

        authorDTO = (AuthorDTO)((Map<String, Object>)controller.insert(authorDTO).getBody()).get("data");

        assertEquals("test@email.com", authorDTO.getEmail());
    }

    @Test
    public void test_getAuthor() {
        authorDTO = AuthorMapper.INSTANCE.map(author);

        Mockito.when(service.getAuthorById(Mockito.anyLong())).thenReturn(authorDTO);

        authorDTO = (AuthorDTO)((Map<String, Object>)controller.getAuthor(123L).getBody()).get("data");

        assertEquals("test@email.com", authorDTO.getEmail());
    }

    @Test
    public void test_getAuthorByEmail() {
        authorDTO = AuthorMapper.INSTANCE.map(author);

        Mockito.when(service.findAuthorByEmail(Mockito.anyString())).thenReturn(authorDTO);

        authorDTO = (AuthorDTO)((Map<String, Object>)controller.getAuthorByEmail("test@email.com").getBody()).get("data");

        assertEquals("test@email.com", authorDTO.getEmail());
    }
}
