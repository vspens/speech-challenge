package com.project.speech.repository;

import com.project.speech.entity.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Vince Spencer Historia
 * Speech Repository
 */
@Component
@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long> {
    /**
     * Get speech by author ID
     * @param id
     * @return List<Speech>
     */
    List<Speech> findAllByAuthorId(Long id);

    /**
     * Find list by date range
     * @param speechDateStart
     * @param speechDateEnd
     * @return
     */
    List<Speech> findBySpeechDateBetween(Date speechDateStart, Date speechDateEnd);

    /**
     * General Search
     * @param stringKeyword
     * @return List<Speech>
     */
    @Query("SELECT s FROM Speech s LEFT JOIN Author a on a.id = s.author " +
            "WHERE CONCAT(s.keywords, ' ', s.speechBody, ' ', " +
            "s.speechDate, ' ', a.email, ' ', a.name ) LIKE %?1%")
    List<Speech> search(String stringKeyword);
}
