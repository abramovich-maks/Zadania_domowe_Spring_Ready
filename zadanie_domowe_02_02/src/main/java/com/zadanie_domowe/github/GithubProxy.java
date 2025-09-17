package com.zadanie_domowe.github;


import com.zadanie_domowe.github.infrastructure.controller.dto.response.BranchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-client", configuration = GithubFeignConfig.class)
public interface GithubProxy {

//    https://api.github.com/users/abramovich-maks/repos

    @GetMapping("/users/{userName}/repos")
    List<GithubResponse> makeSearchRequest(
            @PathVariable String userName
    );


    //    https://api.github.com/repos/abramovich-maks/SproutSync/branches
    @GetMapping("/repos/{userName}/{repoName}/branches")
    List<BranchDTO> makeBranchesRequest(
            @PathVariable String userName,
            @PathVariable String repoName
    );
}
