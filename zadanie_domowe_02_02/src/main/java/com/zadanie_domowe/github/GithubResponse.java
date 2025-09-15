package com.zadanie_domowe.github;

public record GithubResponse(String name,
                             Owner owner,
                             Boolean fork) {
}
