package com.project.speech.mapper;

import com.project.speech.dto.SpeechDTO;
import com.project.speech.entity.Speech;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Vince Spencer Historia
 * Mapper class for Speech
 */
@Mapper
public interface SpeechMapper {
    SpeechMapper INSTANCE = Mappers.getMapper(SpeechMapper.class);

    /**
     * Mapper method from Speech to Speech DTO
     * @param speech
     * @return SpeechDTO
     */
    @Mapping(source = "speech.author.id", target = "authorId")
    @Mapping(source = "speech.author.email", target = "email")
    @Mapping(source = "speech.author.name", target = "name")
    SpeechDTO map(Speech speech);

    /**
     * Mapper method from Speech DTO to Speech
     * @param speechDTO
     * @return Speech
     */
    @Mapping(source = "speechDTO.authorId", target = "author.id")
    @Mapping(source = "speechDTO.email", target = "author.email")
    @Mapping(source = "speechDTO.name", target = "author.name")
    Speech map(SpeechDTO speechDTO);

    /**
     * Mapper method from List<Speech> to List<SpeechDTO>
     * @param speechList
     * @return List<SpeechDTO>
     */
    @Mapping(source = "speechList.speech.author.id", target = "authorId")
    @Mapping(source = "speechList.speech.author.email", target = "email")
    @Mapping(source = "speechList.speech.author.name", target = "name")
    List<SpeechDTO> map(List<Speech> speechList);

}
