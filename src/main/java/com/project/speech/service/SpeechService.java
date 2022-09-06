package com.project.speech.service;

import com.project.speech.dto.SpeechDTO;
import com.project.speech.entity.Author;
import com.project.speech.entity.Speech;
import com.project.speech.mapper.SpeechMapper;
import com.project.speech.repository.AuthorRepository;
import com.project.speech.repository.SpeechRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Vince Spencer Historia
 * Service class for Speech
 */
@Service
public class SpeechService {
    @Autowired
    private SpeechRepository speechRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get speech by Author ID
     * @param authorId
     * @return List<SpeechDTO>
     */
    public List<SpeechDTO> getSpeechList(Long authorId) {
        Author author = authorRepository.findAuthorById(authorId);
        List<SpeechDTO> speechDTOList = new ArrayList<>();
        if (!Objects.nonNull(author)) {
            return speechDTOList;
        }
        return SpeechMapper.INSTANCE.map(speechRepository.findAllByAuthorId(author.getId()));
    }

    /**
     * Save speech
     * @param speech
     * @return SpeechDTO
     */
    public SpeechDTO saveSpeech(Speech speech) {
        speechRepository.save(speech);
        return SpeechMapper.INSTANCE.map(speech);
    }

    /**
     * Get speech by ID
     * @param id
     * @return SpeechDTO
     */
    public SpeechDTO findById(Long id) {
        return SpeechMapper.INSTANCE.map(speechRepository.findById(id).orElse(null));
    }

    /**
     * Delete speech by ID
     * @param id
     */
    public void deleteById(Long id) {
        speechRepository.deleteById(id);
    }

    /**
     * Check if speech exist by ID
     * @param id
     * @return boolean
     */
    public boolean existsById(Long id) {
        return speechRepository.existsById(id);
    }

    /**
     * Update speech record
     * @param id
     * @param speechBody
     * @param keywords
     * @param speechDate
     * @return SpeechDTO
     */
    @Transactional
    public SpeechDTO update(Long id, String speechBody, String keywords, Date speechDate) {
        Speech speech = speechRepository.findById(id).orElse(null);
        if (Objects.nonNull(speech)) {
            if (!StringUtils.isEmpty(speechBody)) {
                speech.setSpeechBody(speechBody);
            }
            if (!StringUtils.isEmpty(keywords)) {
                speech.setKeywords(keywords);
            }
            if (Objects.nonNull(speechDate)) {
                speech.setSpeechDate(speechDate);
            }
        }
        return SpeechMapper.INSTANCE.map(speech);
    }

    /**
     * Search speeches by date range
     * @param start
     * @param end
     * @return List<SpeechDTO>
     */
    public List<SpeechDTO> searchSpeechByDateRange(Date start, Date end) {
        return SpeechMapper.INSTANCE.map(speechRepository.findBySpeechDateBetween(start, end));
    }

    /**
     * Search speeches by author email
     * @param email
     * @return List<SpeechDTO>
     */
    public List<SpeechDTO> findSpeechByAuthorEmail(String email) {
        Author author = authorRepository.findAuthorByEmail(email);
        List<SpeechDTO> speechDTOList = new ArrayList<>();
        if (!Objects.nonNull(author)) {
            return speechDTOList;
        }
        return SpeechMapper.INSTANCE.map(speechRepository.findAllByAuthorId(author.getId()));
    }

    /**
     * General search on all the speeches
     * @param keyword
     * @return List<SpeechDTO>
     */
    public List<SpeechDTO> searchSpeech(String keyword) {
        return SpeechMapper.INSTANCE.map(speechRepository.search(keyword));
    }
}
