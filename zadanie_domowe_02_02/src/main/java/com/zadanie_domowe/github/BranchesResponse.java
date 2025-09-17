package com.zadanie_domowe.github;

import com.zadanie_domowe.github.domain.model.Commit;

public record BranchesResponse(String name, Commit commit) {
}
