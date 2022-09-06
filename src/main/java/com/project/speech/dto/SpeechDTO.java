package com.project.speech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Vince Spencer Historia
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpeechDTO {

    @JsonProperty("id")
    private Long id;

    @NotNull
    @NotEmpty
    @JsonProperty("speechBody")
    private String speechBody;

    @NotNull
    @NotEmpty
    @JsonProperty("keywords")
    private String keywords;

    @NotNull
    @NotEmpty
    @JsonProperty("speechDate")
    private Date speechDate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("author")
    private String name;

    @NotNull
    @NotEmpty
    @JsonProperty("authorId")
    private Long authorId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "SpeechDTO{" +
                "speechBody='" + speechBody + '\'' +
                ", keywords='" + keywords + '\'' +
                ", speechDate=" + speechDate +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
