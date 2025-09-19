package com.zadanie_domowe.github.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class GithubProxy {

    private final RestTemplate restTemplate;

    public GithubProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${github.service.url}")
    String url;

    @Value("${github.token:}")
    String token;


    //    https://api.github.com/users/abramovich-maks/repos
    public String makeUserRepo(String user) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(url)
                .path("/users/" + user + "/repos");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");
            headers.add("User-Agent", "AI-Assistant-GitHubClient"); // GitHub требует User-Agent
            if (token != null && !token.isBlank()) {
                headers.add("Authorization", "Bearer " + token);
            }
            HttpEntity<GithubResponse> entity = new HttpEntity<>(headers);
            headers.add("Accept", "application/json");
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return response.getBody();
        } catch (RestClientResponseException exception) {
            String message = exception.getStatusText() + " " + exception.getStatusCode().value();
            log.error(message);
        } catch (RestClientException exception) {
            String message = exception.getMessage();
            log.error(message);
        }
        return null;
    }

    //    https://api.github.com/repos/abramovich-maks/SproutSync/branches
    public String makeReposBranches(String userName, String repoName) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(url)
                .path("/repos/" + userName + "/" + repoName + "/branches");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");
            headers.add("User-Agent", "zadanie-domowe");
            if (token != null && !token.isBlank()) {
                headers.add("Authorization", "Bearer " + token);
            }
            HttpEntity<BranchesResponse> entity = new HttpEntity<>(headers);
            headers.add("Accept", "application/json");
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            String message = exception.getStatusText() + " " + exception.getStatusCode().value();
            log.error(message);
        } catch (RestClientException exception) {
            String message = exception.getMessage();
            log.error(message);
        }
        return null;
    }
}
