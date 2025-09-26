package com.zadanie_domowe.github.controller;

import com.zadanie_domowe.github.controller.dto.response.RepoWithBranchesDtoResponse;
import com.zadanie_domowe.github.error.UserNotFoundException;
import com.zadanie_domowe.github.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/github",  produces = "application/json")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}/repos")
    public ResponseEntity<List<RepoWithBranchesDtoResponse>> listRepos(@PathVariable String username) {
        List<RepoWithBranchesDtoResponse> allReposWithBranches = githubService.getRepoWithBranches(username);
        if (allReposWithBranches.isEmpty()) {
            throw new UserNotFoundException("UserName: " + username + " not found");        }

        return ResponseEntity.ok(allReposWithBranches);
    }
}