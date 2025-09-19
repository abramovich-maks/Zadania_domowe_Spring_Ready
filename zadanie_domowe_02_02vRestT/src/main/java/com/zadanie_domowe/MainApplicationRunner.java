package com.zadanie_domowe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zadanie_domowe.github.proxy.BranchesResponse;
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
//        List<GithubResult> repos = githubService.fetchUserRepos(userName);
//        for (GithubResult repo : repos) {
//            log.info("Repo: {}, Owner: {}, Fork: {}",
//                    repo.name(),
//                    repo.owner().login(),
//                    repo.fork());
//        }
    }

    public void startBranchRepo(String userName, String repoName) throws JsonProcessingException {
        ResultResponseBranch branchesResponses = githubService.fetchBranchRepos(userName, repoName);
        for (BranchesResponse repo : branchesResponses.responses()) {
            log.info("RepoName: {}, Sha: {}",
                    repo.name(),
                    repo.commit().sha());
        }
    }
}