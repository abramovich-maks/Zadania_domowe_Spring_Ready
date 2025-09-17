package com.zadanie_domowe.github.domain.service;

import com.zadanie_domowe.github.infrastructure.controller.dto.response.BranchDTO;
import com.zadanie_domowe.github.GithubProxy;
import com.zadanie_domowe.github.GithubResponse;
import com.zadanie_domowe.github.infrastructure.controller.dto.response.RepoWithBranchesDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GithubService {

    private final GithubProxy githubProxy;

    public GithubService(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

    public List<GithubResponse> getGithubProxy(String username) {
        return githubProxy.makeSearchRequest(username);
    }

    public List<BranchDTO> getBranches(String userName, String repoName) {
        return githubProxy.makeBranchesRequest(userName, repoName);
    }

    public List<RepoWithBranchesDTO> getRepoWithBranches(String userName) {
        List<GithubResponse> allRepos = githubProxy.makeSearchRequest(userName);

        List<RepoWithBranchesDTO> response = new ArrayList<>();

        for (GithubResponse notFork : allRepos) {
            if (notFork.fork()) {
                continue;
            }

            String owner = notFork.owner().login();
            String repoName = notFork.name();

            List<BranchDTO> allBranches = githubProxy.makeBranchesRequest(owner, repoName).stream()
                    .map(b -> new BranchDTO(b.name(), b.commit()))
                    .toList();
            response.add(new RepoWithBranchesDTO(repoName, owner, allBranches));
        }
        return response;
    }
}
