package com.zadanie_domowe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zadanie_domowe.proxy.SongsProxy;
import com.zadanie_domowe.service.SongsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class ZadanieDomowe0201Application {

    private final SongsService service;

    public ZadanieDomowe0201Application(SongsProxy songsProxy, SongsService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZadanieDomowe0201Application.class, args);
    }


    @EventListener(ApplicationStartedEvent.class)
    public void run() throws JsonProcessingException {
//        service.postSong("Tru-la-la","Peppa pig");
        service.fetchAllSongs();
//        service.fetchSongById("2");
//        service.deleteSongById("4");
//        service.putSong("2","Tru-la-la","Peppa pig");
    }


}
