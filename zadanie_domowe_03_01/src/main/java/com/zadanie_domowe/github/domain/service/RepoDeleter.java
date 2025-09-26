package com.zadanie_domowe.github.domain.service;

import com.zadanie_domowe.github.domain.repository.GithubRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class RepoDeleter {

    private final GithubRepository githubRepository;

    public RepoDeleter(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public void deleteById(Long id) {
        githubRepository.deleteById(id);
        log.info("Deleted repository by id: {}", id);
    }

    public void deleteAllRepoByOwner(String owner) {
        githubRepository.deleteAllByOwner(owner);
        log.info("Deleted all repository by owner: {}", owner);
    }
}