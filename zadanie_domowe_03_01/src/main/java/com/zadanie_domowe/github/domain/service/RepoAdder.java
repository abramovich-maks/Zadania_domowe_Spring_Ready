package com.zadanie_domowe.github.domain.service;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import com.zadanie_domowe.github.domain.repository.GithubRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RepoAdder {

    private final GithubRepository githubRepository;

    public RepoAdder(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public RepoEntity addRepo(RepoEntity entity) {
        log.info("Added new repository {} with owner {}.", entity.getName(), entity.getOwner());
        return githubRepository.save(entity);
    }
}
