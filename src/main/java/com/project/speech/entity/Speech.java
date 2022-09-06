package com.project.speech.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Vince Spencer Historia
 */
@Entity
@Table(name= "Speech")
public class Speech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.StringType")
    @Column(name = "SPEECH_BODY", nullable = false, unique = false)
    private String speechBody;

    @Column(name = "KEYWORDS", length = 100, nullable = false, unique = false)
    private String keywords;

    @Temporal(TemporalType.DATE)
    @Column(name = "SPEECH_DATE", nullable = false, unique = false)
    private Date speechDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeechBody() {
        return speechBody;
    }

    public void setSpeechBody(String speechBody) {
        this.speechBody = speechBody;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Date getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(Date speechDate) {
        this.speechDate = speechDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
