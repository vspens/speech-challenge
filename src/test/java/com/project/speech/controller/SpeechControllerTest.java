package com.project.speech.controller;

import com.project.speech.dto.SpeechDTO;
import com.project.speech.entity.Author;
import com.project.speech.entity.Speech;
import com.project.speech.mapper.SpeechMapper;
import com.project.speech.service.SpeechService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class SpeechControllerTest {

    @InjectMocks
    private SpeechController controller;

    @Mock
    private SpeechService service;

    private SpeechDTO speechDTO;
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
    public void test_list() {
        speechDTO = SpeechMapper.INSTANCE.map(speech);
        List<SpeechDTO> speechList = Collections.singletonList(speechDTO);

        Mockito.when(service.getSpeechList(Mockito.anyLong())).thenReturn(speechList);

        speechList = (List<SpeechDTO>) ((Map<String, Object>)controller.list(123L).getBody()).get("data");

        assertEquals("Hooray!", speechList.get(0).getSpeechBody());
    }

    @Test
    public void test_insert() {
        speechDTO = SpeechMapper.INSTANCE.map(speech);

        Mockito.when(service.saveSpeech(Mockito.any(Speech.class))).thenReturn(speechDTO);

        speechDTO = (SpeechDTO)((Map<String, Object>)controller.insert(speechDTO).getBody()).get("data");

        assertEquals("Hooray!", speechDTO.getSpeechBody());
    }

    @Test
    public void test_delete() {
        Mockito.when(service.existsById(Mockito.anyLong())).thenReturn(true);
        doNothing().when(service).deleteById(Mockito.anyLong());

        Object id = (Object)((Map<String, Object>)controller.delete(123L).getBody()).get("data");

        assertEquals(123L, Long.parseLong(id.toString()));
    }

    @Test
    public void test_update() throws ParseException {
        speech.setSpeechBody("Hooray! updated");
        speechDTO = SpeechMapper.INSTANCE.map(speech);

        Mockito.when(service.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(service.update(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.any(Date.class))).thenReturn(speechDTO);

        speechDTO = ((SpeechDTO)((Map<String, Object>)controller.update(speechDTO).getBody()).get("data"));

        assertEquals("Hooray! updated", speechDTO.getSpeechBody());
    }

    @Test
    public void test_search() {
        speechDTO = SpeechMapper.INSTANCE.map(speech);
        List<SpeechDTO> speechList = Collections.singletonList(speechDTO);

        Mockito.when(service.searchSpeech(Mockito.anyString())).thenReturn(speechList);

        speechList = (List<SpeechDTO>) ((Map<String, Object>)controller.search("Hooray!").getBody()).get("data");

        assertEquals("Hooray!", speechList.get(0).getSpeechBody());
    }

    @Test
    public void test_searchSpeechByDate() throws ParseException {
        speech.setSpeechDate(DateUtils.parseDate("2022-09-06", "yyyy-MM-dd"));
        speechDTO = SpeechMapper.INSTANCE.map(speech);
        List<SpeechDTO> speechList = Collections.singletonList(speechDTO);

        Mockito.when(service.searchSpeechByDateRange(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(speechList);

        speechList = (List<SpeechDTO>) ((Map<String, Object>)controller.searchSpeechByDate("2022-09-06", "2022-09-06").getBody()).get("data");

        assertEquals(DateUtils.parseDate("2022-09-06", "yyyy-MM-dd"), speechList.get(0).getSpeechDate());
    }

    @Test
    public void test_searchSpeechByAuthor() {
        speech.getAuthor().setEmail("vincehistoria@gmail.com");
        speechDTO = SpeechMapper.INSTANCE.map(speech);
        List<SpeechDTO> speechList = Collections.singletonList(speechDTO);

        Mockito.when(service.findSpeechByAuthorEmail(Mockito.anyString())).thenReturn(speechList);

        speechList = (List<SpeechDTO>) ((Map<String, Object>)controller.searchSpeechByAuthor("vincehistoria@gmail.com").getBody()).get("data");

        assertEquals("vincehistoria@gmail.com", speechList.get(0).getEmail());
    }
}
