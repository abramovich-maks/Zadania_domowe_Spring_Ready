package com.zadanie_domowe.github;

import org.springframework.stereotype.Service;

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

    public List<BranchesResponse> getBranches(String userName, String repoName) {
        return githubProxy.makeBranchesRequest(userName, repoName);
    }
}
