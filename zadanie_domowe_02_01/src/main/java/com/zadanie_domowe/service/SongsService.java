package com.zadanie_domowe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zadanie_domowe.proxy.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class SongsService {

    private final SongsProxy songsProxy;
    private final SongsMapper songsMapper;

    public SongsService(SongsProxy songsProxy, SongsMapper songsMapper) {
        this.songsProxy = songsProxy;
        this.songsMapper = songsMapper;
    }

    public void fetchAllSongs() {
        String json = songsProxy.makeGetAllSongsRequest();
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return;
        }
        ResponseSongs responseSongs = songsMapper.mapJsonToSongsResponse(json);
        log.info(responseSongs.songs());
    }

    public void fetchSongById(String id) {
        String json = songsProxy.makeGetSongsByIdRequest(id);
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return;
        }
        SongByIdResponse responseSongs = songsMapper.mapJsonToSongEntity(json);
        log.info("Song by id:" + id + " - " + responseSongs);
    }

    public void postSong(String song, String artist) {
        String json = songsProxy.makePostSong(song, artist);
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return;
        }
        SongByIdResponse songResponse = songsMapper.mapJsonToSongEntity(json);
        log.info("New Song added: " + songResponse.song());
    }

    public void deleteSongById(String id) {
        String json = songsProxy.makeDeleteSongByIdRequest(id);
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return;
        }
        songsMapper.mapJsonToSongsResponse(json);
        log.info("Song by id:" + id + " - have been deleted");

    }

    public void putSong(String id, String song, String artist) {
        String json = songsProxy.makePutSongByIdRequest(id, song, artist);
        if (json == null) {
            log.error(getJsonSongsWasNull());
            return;
        }
        songsMapper.mapJsonToSongsResponse(json);
        log.info("Song with id: " + id + " have been updated");
    }


    private static String getJsonSongsWasNull() {
        return "jsonSongs was null";
    }
}