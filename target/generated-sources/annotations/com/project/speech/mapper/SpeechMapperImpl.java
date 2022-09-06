package com.project.speech.mapper;

import com.project.speech.dto.SpeechDTO;
import com.project.speech.entity.Author;
import com.project.speech.entity.Speech;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-06T02:11:23+0800",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220719-0747, environment: Java 17.0.4 (Eclipse Adoptium)"
)
public class SpeechMapperImpl implements SpeechMapper {

    @Override
    public SpeechDTO map(Speech speech) {
        if ( speech == null ) {
            return null;
        }

        SpeechDTO speechDTO = new SpeechDTO();

        speechDTO.setAuthorId( speechAuthorId( speech ) );
        speechDTO.setEmail( speechAuthorEmail( speech ) );
        speechDTO.setName( speechAuthorName( speech ) );
        speechDTO.setId( speech.getId() );
        speechDTO.setSpeechBody( speech.getSpeechBody() );
        speechDTO.setKeywords( speech.getKeywords() );
        speechDTO.setSpeechDate( speech.getSpeechDate() );

        return speechDTO;
    }

    @Override
    public Speech map(SpeechDTO speechDTO) {
        if ( speechDTO == null ) {
            return null;
        }

        Speech speech = new Speech();

        speech.setAuthor( speechDTOToAuthor( speechDTO ) );
        speech.setId( speechDTO.getId() );
        speech.setSpeechBody( speechDTO.getSpeechBody() );
        speech.setKeywords( speechDTO.getKeywords() );
        speech.setSpeechDate( speechDTO.getSpeechDate() );

        return speech;
    }

    @Override
    public List<SpeechDTO> map(List<Speech> speechList) {
        if ( speechList == null ) {
            return null;
        }

        List<SpeechDTO> list = new ArrayList<SpeechDTO>( speechList.size() );
        for ( Speech speech : speechList ) {
            list.add( map( speech ) );
        }

        return list;
    }

    private Long speechAuthorId(Speech speech) {
        if ( speech == null ) {
            return null;
        }
        Author author = speech.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String speechAuthorEmail(Speech speech) {
        if ( speech == null ) {
            return null;
        }
        Author author = speech.getAuthor();
        if ( author == null ) {
            return null;
        }
        String email = author.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String speechAuthorName(Speech speech) {
        if ( speech == null ) {
            return null;
        }
        Author author = speech.getAuthor();
        if ( author == null ) {
            return null;
        }
        String name = author.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Author speechDTOToAuthor(SpeechDTO speechDTO) {
        if ( speechDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( speechDTO.getAuthorId() );
        author.setEmail( speechDTO.getEmail() );
        author.setName( speechDTO.getName() );

        return author;
    }
}
