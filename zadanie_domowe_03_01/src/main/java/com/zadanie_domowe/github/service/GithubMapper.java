package com.zadanie_domowe.github.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadanie_domowe.github.proxy.BranchesResponse;
import com.zadanie_domowe.github.proxy.GithubResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class GithubMapper {

    private final ObjectMapper objectMapper;

    public GithubMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public GithubResult[] mapJsonToGithubResults(String json) {
        try {
            return objectMapper.readValue(json, GithubResult[].class);
        } catch (JsonProcessingException e) {
            log.error("GithubMapper could not map json");
        }
        return new GithubResult[0];
    }

    public BranchesResponse[] mapJsonToBranches(String json) {
        try {
            return objectMapper.readValue(json, BranchesResponse[].class);
        } catch (JsonProcessingException e) {
            log.error("GithubMapper could not map json");
        }
        return new BranchesResponse[0];
    }
}
