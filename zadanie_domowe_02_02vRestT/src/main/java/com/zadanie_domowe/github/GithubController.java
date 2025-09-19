package com.zadanie_domowe.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zadanie_domowe.github.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}/repos")
    public ResponseEntity<List<RepoWithBranchesDTO>> listRepos(@PathVariable String username) throws JsonProcessingException {
        List<RepoWithBranchesDTO> allReposWithBranches = githubService.getRepoWithBranches(username);
        return ResponseEntity.ok(allReposWithBranches);
    }
}
