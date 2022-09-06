package com.project.speech.mapper;

import com.project.speech.dto.AuthorDTO;
import com.project.speech.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Vince Spencer Historia
 * Mapper class for Author
 */
@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    /**
     * Mapper method from Author to Author DTO
     * @param author
     * @return AuthorDTO
     */
    AuthorDTO map(Author author);

    /**
     * Mapper method from Author DTO to Author
     * @param authorDTO
     * @return Author
     */
    Author map(AuthorDTO authorDTO);
}
