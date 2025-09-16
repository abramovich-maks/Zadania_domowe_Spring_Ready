package com.zadanie_domowe.github;

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

    public List<BranchesResponse> getBranches(String userName, String repoName) {
        return githubProxy.makeBranchesRequest(userName, repoName);
    }

    public List<UserResponse> getRepoWithBranches(String userName) {
        List<GithubResponse> allRepos = githubProxy.makeSearchRequest(userName);

        List<UserResponse> response = new ArrayList<>();

        for (GithubResponse notFork : allRepos) {
            if (notFork.fork()) {
                continue;
            }

            String owner = notFork.owner().login();
            String repoName = notFork.name();

            List<BranchesResponse> allBranches = githubProxy.makeBranchesRequest(owner, repoName).stream()
                    .map(b -> new BranchesResponse(b.name(), b.commit()))
                    .toList();
            response.add(new UserResponse(repoName, owner, allBranches));
        }
        return response;
    }
}
