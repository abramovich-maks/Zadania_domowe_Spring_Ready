package com.zadanie_domowe.github.infrastructure.controller;

import com.zadanie_domowe.github.BranchesResponse;
import com.zadanie_domowe.github.domain.service.GithubService;
import com.zadanie_domowe.github.UserResponse;
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
    public List<BranchesResponse> listBranches(@PathVariable String userName, @PathVariable String repoName) {
        return githubService.getBranches(userName,repoName);
    }

    @GetMapping("/{username}/repos")
    public List<UserResponse> listRepos(@PathVariable String username) {
        return githubService.getRepoWithBranches(username);
    }
}
