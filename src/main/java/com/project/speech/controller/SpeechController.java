package com.project.speech.controller;

import com.project.speech.dto.SpeechDTO;
import com.project.speech.entity.Speech;
import com.project.speech.mapper.SpeechMapper;
import com.project.speech.response.ResponseHandler;
import com.project.speech.service.SpeechService;
import com.project.speech.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

/**
 * @author Vince Spencer Historia
 * Handles API for Speech
 */
@RestController
@RequestMapping("/api/speech")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    /**
     * Get the list of speeches by ID
     * @param id
     * @return ResponseEntity<Object>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> list(@PathVariable("id") Long id) {
        List<SpeechDTO> speechDTOList = speechService.getSpeechList(id);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, speechDTOList);
    }

    /**
     * Save a speech
     * Example JSON request
     * {
    *      "speechBody" : "Hello this is a sample speech",
     *     "keywords" : "sample",
     *     "speechDate" : "yyyy-MM-dd",
     *     "authorId" : "1"
     * }
     * @param speechDTO
     * @return ResponseEntity<Object>
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody SpeechDTO speechDTO) {
        Speech speech = SpeechMapper.INSTANCE.map(speechDTO);
        SpeechDTO speechDTOResult = speechService.saveSpeech(speech);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, speechDTOResult);
    }

    /**
     * Delete a speech by speech ID
     * @param id
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        if (!speechService.existsById(id)) {
            return ResponseHandler.generateResponse("ID does not exist", HttpStatus.BAD_REQUEST, id);
        }
        speechService.deleteById(id);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, id);
    }

    /**
     * Update a speech metadata
     * @return ResponseEntity<Object>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody SpeechDTO speechDTO) throws ParseException {

        if (StringUtils.isEmpty(speechDTO.getSpeechBody())
                && StringUtils.isEmpty(speechDTO.getKeywords())
                && !Objects.nonNull(speechDTO.getSpeechDate())) {
            return ResponseHandler.generateResponse("Please specify update parameters", HttpStatus.BAD_REQUEST, speechDTO.getId());
        }

        SpeechDTO speechDTOResult;
        if (!speechService.existsById(speechDTO.getId())) {
            return ResponseHandler.generateResponse("ID does not exist", HttpStatus.BAD_REQUEST, speechDTO.getId());
        }
        speechDTOResult = speechService.update(speechDTO.getId(), speechDTO.getSpeechBody(),
                speechDTO.getKeywords(), speechDTO.getSpeechDate());
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, speechDTOResult);
    }

    /**
     * Provides general search
     * @param search
     * @return ResponseEntity<Object>
     */
    @GetMapping("/search")
    public ResponseEntity<Object> search(
            @RequestParam(name = "search") String search) {
        return ResponseHandler.generateResponse(
                "Success",
                HttpStatus.OK,
                speechService.searchSpeech(search));
    }

    /**
     * Search a speech by date range
     * @param start
     * @param end
     * @return ResponseEntity<Object>
     */
    @GetMapping("/search/date")
    public ResponseEntity<Object> searchSpeechByDate(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end) throws ParseException {
        return ResponseHandler.generateResponse(
                "Success",
                HttpStatus.OK,
                speechService.searchSpeechByDateRange(
                        DateUtils.parseDate(start, "yyyy-MM-dd"),
                        DateUtils.parseDate(end, "yyyy-MM-dd")));
    }

    /**
     * Searches all the speech of an author
     * @param email
     * @return ResponseEntity<Object>
     */
    @GetMapping("/search/author")
    public ResponseEntity<Object> searchSpeechByAuthor(
            @RequestParam(name = "email") String email) {
        if (!Validator.email(email)) {
            return ResponseHandler.generateResponse("must be a well-formed email address", HttpStatus.BAD_REQUEST, email);
        }
        return ResponseHandler.generateResponse(
                "Success",
                HttpStatus.OK,
                speechService.findSpeechByAuthorEmail(email));
    }

}
