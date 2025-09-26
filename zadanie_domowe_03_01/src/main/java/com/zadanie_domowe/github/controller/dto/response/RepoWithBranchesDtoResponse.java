package com.zadanie_domowe.github.controller.dto.response;

import java.util.List;

public record RepoWithBranchesDtoResponse(String repoName, String ownerLogin, List<BranchDtoResponse> branches) {
}
