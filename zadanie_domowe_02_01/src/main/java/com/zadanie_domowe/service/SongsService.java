package com.zadanie_domowe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadanie_domowe.proxy.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class SongsService {

    private final SongsProxy songsProxy;

    public SongsService(SongsProxy songsProxy) {
        this.songsProxy = songsProxy;
    }

    public void fetchAllSongs() throws JsonProcessingException {
        String json = songsProxy.makeGetAllSongsRequest();
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            ResponseSongs song = mapper.readValue(json, ResponseSongs.class);
            log.info(song.songs());
        }
    }

    public void fetchSongById(String id) throws JsonProcessingException {
        String json = songsProxy.makeGetSongsByIdRequest(id);
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            SongByIdResponse song = mapper.readValue(json, SongByIdResponse.class);
            log.info("Song by id:" + id + " - " + song.song());
        }
    }

    public void postSong(String song, String artist) throws JsonProcessingException {
        String json = songsProxy.makePostSong(song, artist);
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            SongByIdResponse newSong = mapper.readValue(json, SongByIdResponse.class);
            log.info("New Song added: " + newSong.song());
        }
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
}