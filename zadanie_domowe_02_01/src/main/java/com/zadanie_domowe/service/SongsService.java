package com.zadanie_domowe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadanie_domowe.proxy.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;


@Service
@Log4j2
public class SongsService {

    private final SongsProxy songsProxy;
    private final SongsMapper songsMapper;

    public SongsService(SongsProxy songsProxy, SongsMapper songsMapper) {
        this.songsProxy = songsProxy;
        this.songsMapper = songsMapper;
    }

    public Map<Integer, SongEntity> fetchAllSongs() {
        String json = songsProxy.makeGetAllSongsRequest();
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return Collections.emptyMap();
        }
        ResponseSongs responseSongs = songsMapper.mapJsonToSongsResponse(json);
        log.info(responseSongs.songs());
        return responseSongs.songs();
    }

    public SongByIdResponse fetchSongById(String id) {
        String json = songsProxy.makeGetSongsByIdRequest(id);
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return new SongByIdResponse(new SongEntity("", ""));
        }
        SongByIdResponse responseSongs = songsMapper.mapJsonToSongEntity(json);
        log.info("Song by id:" + id + " - " + responseSongs);
        return responseSongs;
    }

    public SongByIdResponse postSong(String song, String artist) {
        String json = songsProxy.makePostSong(song, artist);
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return new SongByIdResponse(new SongEntity("", ""));
        }
        SongByIdResponse songResponse = songsMapper.mapJsonToSongEntity(json);
        log.info("New Song added: " + songResponse.song());
        return songResponse;
    }

    public void deleteSongById(String id) throws JsonProcessingException {
        String json = songsProxy.makeDeleteSongByIdRequest(id);
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(json, ResponseSongs.class);
            log.info("Song by id:" + id + " - have been deleted");
        }
    }

    public void putSong(String id, String song, String artist) throws JsonProcessingException {
        String json = songsProxy.makePutSongByIdRequest(id, song, artist);
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(json, ResponseSongs.class);
            log.info("Song with id: " + id + " have been updated");
        }
    }

    private static String getJsonSongsWasNull() {
        return "jsonSongs was null";
    }
}