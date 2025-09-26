package com.zadanie_domowe.github.domain.service;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import com.zadanie_domowe.github.domain.repository.GithubRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RepoRetriever {

    private final GithubRepository githubRepository;

    public RepoRetriever(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public List<RepoEntity> findAllRepo() {
        return githubRepository.findAll();
    }

    public List<RepoEntity> findRepoByOwner(String owner) {
        return githubRepository.findRepoByOwner(owner);
    }
}
