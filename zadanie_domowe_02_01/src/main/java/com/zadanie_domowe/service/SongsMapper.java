package com.zadanie_domowe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadanie_domowe.proxy.ResponseSongs;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Log4j2
public class SongsMapper {

    private final ObjectMapper objectMapper;

    public SongsMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseSongs mapJsonToSongsResponse(String json) {
        try {
            return objectMapper.readValue(json, ResponseSongs.class);
        } catch (JsonProcessingException e) {
            log.error("SongsMapper could not map json");
            return new ResponseSongs(Collections.emptyMap());
        }
    }
}
