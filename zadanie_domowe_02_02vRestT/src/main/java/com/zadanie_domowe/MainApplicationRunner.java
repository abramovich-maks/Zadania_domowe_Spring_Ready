package com.zadanie_domowe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zadanie_domowe.github.RepoWithBranchesDTO;
import com.zadanie_domowe.github.proxy.BranchesResponse;
import com.zadanie_domowe.github.proxy.GithubResponse;
import com.zadanie_domowe.github.proxy.GithubResult;
import com.zadanie_domowe.github.proxy.ResultResponseBranch;
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

    public void start(String userName) throws JsonProcessingException {
        List<RepoWithBranchesDTO> allReposWithBranches = githubService.getRepoWithBranches(userName);
        for (RepoWithBranchesDTO repo : allReposWithBranches) {
            log.info("Repo name {} | Owner: {} | Branches: {}",
                    repo.repoName(),
                    repo.ownerLogin(),
                    repo.branches());
        }
    }
}