package com.zadanie_domowe;

import com.zadanie_domowe.github.controller.RepoWithBranchesDTO;
import com.zadanie_domowe.github.service.GithubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class MainApplicationRunner {

    private final GithubService githubService;

    public MainApplicationRunner(GithubService githubService) {
        this.githubService = githubService;
    }

    public void start(String userName) {
        List<RepoWithBranchesDTO> allReposWithBranches = githubService.getRepoWithBranches(userName);
        for (RepoWithBranchesDTO repo : allReposWithBranches) {
            log.info("Repo name {} | Owner: {} | Branches: {}",
                    repo.repoName(),
                    repo.ownerLogin(),
                    repo.branches());
        }
    }
}