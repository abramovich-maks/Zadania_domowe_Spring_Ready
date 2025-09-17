package com.zadanie_domowe.github;

import com.zadanie_domowe.github.domain.model.Owner;

public record GithubResponse(String name,
                             Owner owner,
                             Boolean fork) {
}
