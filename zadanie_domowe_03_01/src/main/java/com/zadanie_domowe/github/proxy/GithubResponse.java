package com.zadanie_domowe.github.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubResponse(List<GithubResult> resultList) {
}
