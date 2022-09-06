package com.project.speech.service;

import com.project.speech.entity.Author;
import com.project.speech.entity.Speech;
import com.project.speech.repository.AuthorRepository;
import com.project.speech.repository.SpeechRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SpeechServiceTest {

    @InjectMocks
    private SpeechService speechService;

    @Mock
    private SpeechRepository speechRepository;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;

    private Speech speech;

    @Before
    public void setUp() {
        author = new Author();
        author.setEmail("test@email.com");
        author.setName("John Doe");
        author.setId(123L);
        speech = new Speech();
        speech.setSpeechBody("Hooray!");
        speech.setSpeechDate(new Date());
        speech.setKeywords("excite");
        speech.setAuthor(author);
        speech.setId(123L);
    }

    @Test
    public void test_getSpeechList() {
        List<Speech> speechList = Collections.singletonList(speech);

        Mockito.when(authorRepository.findAuthorById(Mockito.anyLong())).thenReturn(author);
        Mockito.when(speechRepository.findAllByAuthorId(Mockito.anyLong())).thenReturn(speechList);

        assertEquals(speechService.getSpeechList(123L).get(0).getSpeechBody(), "Hooray!");
    }

    @Test
    public void test_saveSpeech() {
        Mockito.when(speechRepository.save(Mockito.any(Speech.class))).thenReturn(speech);

        assertEquals(speechService.saveSpeech(speech).getKeywords(), "excite");
    }

    @Test
    public void test_findById_speechNotNull() {
        Mockito.when(speechRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(speech));

        assertEquals(speechService.findById(123L).getSpeechBody(), "Hooray!");
    }

    @Test
    public void test_findById_speechNull() {
        speech = null;
        Mockito.when(speechRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(speech));

        assertNull(speechService.findById(123L));
    }

    @Test
    public void test_deleteById() {
        Mockito.doNothing().when(speechRepository).deleteById(Mockito.anyLong());

        speechService.deleteById(123L);

        Mockito.verify(speechRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void test_existsById_true() {
        Mockito.when(speechRepository.existsById(Mockito.anyLong())).thenReturn(true);

        assertTrue(speechService.existsById(123L));
    }

    @Test
    public void test_existsById_false() {
        Mockito.when(speechRepository.existsById(Mockito.anyLong())).thenReturn(false);

        assertFalse(speechService.existsById(123L));
    }

    @Test
    public void test_update_notNull() throws ParseException {
        Mockito.when(speechRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(speech));
        assertEquals(speechService.update(123L, "Yahoo!", "adventure", new Date()).getSpeechBody(), "Yahoo!");
        assertEquals(speechService.update(123L, "Yahoo!", "adventure", new Date()).getKeywords(), "adventure");
        assertEquals(speechService.update(123L, "Yahoo!", "adventure", DateUtils.parseDate("2022-09-06", "yyyy-MM-dd")).getSpeechDate(), DateUtils.parseDate("2022-09-06", "yyyy-MM-dd"));
    }

    @Test
    public void test_update_null() {
        speech = null;
        Mockito.when(speechRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(speech));

        assertNull(speechService.update(123L, "Yahoo!", "adventure", new Date()));
    }

    @Test
    public void test_searchSpeechByDateRange() {
        List<Speech> speechList = Collections.singletonList(speech);

        Mockito.when(speechRepository.findBySpeechDateBetween(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(speechList);

        assertEquals(speechService.searchSpeechByDateRange(new Date(),  new Date()).get(0).getSpeechBody(), "Hooray!");
    }

    @Test
    public void test_findSpeechByAuthorEmail() {
        List<Speech> speechList = Collections.singletonList(speech);

        Mockito.when(authorRepository.findAuthorByEmail(Mockito.anyString())).thenReturn(author);
        Mockito.when(speechRepository.findAllByAuthorId(Mockito.anyLong())).thenReturn(speechList);

        assertEquals(speechService.findSpeechByAuthorEmail("test@email.com").get(0).getSpeechBody(), "Hooray!");
    }

    @Test
    public void test_searchSpeech() {
        List<Speech> speechList = Collections.singletonList(speech);

        Mockito.when(speechRepository.search(Mockito.anyString())).thenReturn(speechList);

        assertEquals(speechService.searchSpeech("Hooray!").get(0).getSpeechBody(), "Hooray!");
    }

}