package com.zadanie_domowe.github.infrastructure.controller;

import com.zadanie_domowe.github.infrastructure.controller.dto.response.BranchDTO;
import com.zadanie_domowe.github.domain.service.GithubService;
import com.zadanie_domowe.github.infrastructure.controller.dto.response.RepoWithBranchesDTO;
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

//    @GetMapping("/{username}/repos")
//    public List<GithubResponse> listRepos(@PathVariable String username) {
//       return githubService.getGithubProxy(username);
//    }

    @GetMapping("/{userName}/{repoName}/branches")
    public List<BranchDTO> listBranches(@PathVariable String userName, @PathVariable String repoName) {
        return githubService.getBranches(userName, repoName);
    }

    @GetMapping("/{username}/repos")
    public ResponseEntity<List<RepoWithBranchesDTO>> listRepos(@PathVariable String username) {
        List<RepoWithBranchesDTO> allReposWithBranches = githubService.getRepoWithBranches(username);
        return ResponseEntity.ok(allReposWithBranches);
    }
}
