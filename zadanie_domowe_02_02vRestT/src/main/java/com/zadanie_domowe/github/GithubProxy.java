package com.zadanie_domowe.github;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@Log4j2
public class GithubProxy {

    private final RestTemplate restTemplate;

    public GithubProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${github.service.url}")
    String url;

    //    https://api.github.com/users/abramovich-maks/repos
    public String makeUserRepo(String user) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(url)
                .path("/users/" + user + "/repos");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        ResponseEntity<String> response = restTemplate.exchange(
                builder.build().toUri(),
                HttpMethod.GET,
                null,
                String.class
        );

        return response.getBody();
    }
}
