package com.project.speech.mapper;

import com.project.speech.dto.AuthorDTO;
import com.project.speech.entity.Author;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-06T02:11:23+0800",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220719-0747, environment: Java 17.0.4 (Eclipse Adoptium)"
)
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDTO map(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setId( author.getId() );
        authorDTO.setEmail( author.getEmail() );
        authorDTO.setName( author.getName() );

        return authorDTO;
    }

    @Override
    public Author map(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDTO.getId() );
        author.setEmail( authorDTO.getEmail() );
        author.setName( authorDTO.getName() );

        return author;
    }
}
