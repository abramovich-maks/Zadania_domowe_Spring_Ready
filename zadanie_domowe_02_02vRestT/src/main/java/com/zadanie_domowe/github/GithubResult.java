package com.zadanie_domowe.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubResult(String name,
                           Owner owner,
                           Boolean fork) {
}