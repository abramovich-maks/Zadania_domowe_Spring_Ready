package com.zadanie_domowe.github.infrastructure.controller.dto.response;

import java.util.List;

public record RepoWithBranchesDTO(String repoName, String ownerLogin, List<BranchDTO> branches) {
}
