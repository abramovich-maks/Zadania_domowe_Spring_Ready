package com.zadanie_domowe.github;

import java.util.List;

public record UserResponse(String repoName, String ownerLogin, List<BranchesResponse> branches) {
}
