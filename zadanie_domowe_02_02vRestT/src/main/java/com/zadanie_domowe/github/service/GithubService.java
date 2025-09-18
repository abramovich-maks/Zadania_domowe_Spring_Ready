package com.zadanie_domowe.github.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadanie_domowe.github.proxy.GithubProxy;
import com.zadanie_domowe.github.proxy.GithubResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class GithubService {

    private final GithubProxy githubClient;
    private final ObjectMapper objectMapper;

    public GithubService(GithubProxy githubClient, ObjectMapper objectMapper) {
        this.githubClient = githubClient;
        this.objectMapper = objectMapper;
    }

    public List<GithubResult> fetchUserRepos(String userName) throws JsonProcessingException {
        String json = githubClient.makeUserRepo(userName);
        if (json == null) {
            log.error("User not found");
            return Collections.emptyList();
        }

        List<GithubResult> response = objectMapper.readValue(
                json,
                new TypeReference<List<GithubResult>>() {}
        );

        return response;
    }

}
