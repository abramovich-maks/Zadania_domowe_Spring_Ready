package com.zadanie_domowe.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
@Log4j2
public class SongsProxy {

    @Autowired
    RestTemplate restTemplate;

    @Value("${songs-server.service.url}")
    String url;

    @Value("${songs-server.service.port}")
    String port;

    public String makeGetAllSongsRequest() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs");
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return exchange.getBody();
        } catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return "";
    }

    public String makeGetSongsByIdRequest(String id) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs/" + id);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return exchange.getBody();
        } catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return "";
    }

    public String makePostSong(String song, String artist) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs");
        SongInfo newSong = new SongInfo(song, artist);
        HttpEntity<SongInfo> httpEntity = new HttpEntity<>(newSong);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            return exchange.getBody();
        } catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return "";
    }

    public String makeDeleteSongByIdRequest(String id) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs/" + id);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.DELETE,
                    null,
                    String.class
            );
            return exchange.getBody();
        } catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return "";
    }

    public String makePutSongByIdRequest(String id, String song, String artist) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs/" + id);
        SongInfo newSong = new SongInfo(song, artist);
        HttpEntity<SongInfo> httpEntity = new HttpEntity<>(newSong);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.PUT,
                    httpEntity,
                    String.class
            );
            return exchange.getBody();
        } catch (RestClientResponseException exception) {
            log.info(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return "";
    }
}
