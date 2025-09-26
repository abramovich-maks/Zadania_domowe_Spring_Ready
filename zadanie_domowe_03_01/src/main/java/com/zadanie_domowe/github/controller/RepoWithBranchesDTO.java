package com.zadanie_domowe.github.controller;

import java.util.List;

public record RepoWithBranchesDTO(String repoName, String ownerLogin, List<BranchDTO> branches) {
}
