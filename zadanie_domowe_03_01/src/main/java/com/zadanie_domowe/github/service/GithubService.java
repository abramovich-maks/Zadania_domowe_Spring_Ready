package com.zadanie_domowe.github.service;

import com.zadanie_domowe.github.controller.BranchDTO;
import com.zadanie_domowe.github.controller.RepoWithBranchesDTO;
import com.zadanie_domowe.github.proxy.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class GithubService {

    private final GithubProxy githubClient;
    private final GithubMapper githubMapper;

    public GithubService(GithubProxy githubClient, GithubMapper githubMapper) {
        this.githubClient = githubClient;
        this.githubMapper = githubMapper;
    }


    private GithubResponse fetchUserRepos(String userName) {
        String json = githubClient.makeUserRepo(userName);
        if (json == null) {
            log.error("json was null ");
            return new GithubResponse(new ArrayList<>());
        }
        GithubResult[] githubResults = githubMapper.mapJsonToGithubResults(json);
        List<GithubResult> list = Arrays.asList(githubResults);
        return new GithubResponse(new ArrayList<>(list));
    }



    private ResultResponseBranch fetchBranchRepos(String userName, String repoName) {
        String json = githubClient.makeReposBranches(userName, repoName);
        if (json == null) {
            log.error("json was null ");
            return new ResultResponseBranch(new ArrayList<>());
        }
        BranchesResponse[] response = githubMapper.mapJsonToBranches(json);
        List<BranchesResponse> list = Arrays.asList(response);
        return new ResultResponseBranch(new ArrayList<>(list));
    }



    public List<RepoWithBranchesDTO> getRepoWithBranches(String userName) {
        if (userName == null || userName.isBlank()) {
            log.warn("Empty username");
            return Collections.emptyList();
        }
        GithubResponse repos = fetchUserRepos(userName);
        List<RepoWithBranchesDTO> response = new ArrayList<>();

        for (GithubResult notFork : repos.resultList()) {
            if (notFork.fork()) {
                continue;
            }

            String owner = notFork.owner().login();
            String repoName = notFork.name();

            List<BranchDTO> allBranches = fetchBranchRepos(owner, repoName).responses().stream()
                    .map(b -> new BranchDTO(b.name(), b.commit()))
                    .toList();
            response.add(new RepoWithBranchesDTO(repoName, owner, allBranches));
        }
        return response;
    }
}
