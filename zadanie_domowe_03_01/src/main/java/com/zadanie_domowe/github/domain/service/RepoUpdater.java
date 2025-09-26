package com.zadanie_domowe.github.domain.service;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import com.zadanie_domowe.github.domain.repository.GithubRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RepoUpdater {

    private final GithubRepository githubRepository;
    private  final RepoRetriever repoRetriever;

    public RepoUpdater(GithubRepository githubRepository, RepoRetriever repoRetriever) {
        this.githubRepository = githubRepository;
        this.repoRetriever = repoRetriever;
    }

    public void updateById(Long id, RepoEntity newRepo) {
        repoRetriever.existById(id);
        githubRepository.updateById(id, newRepo);
    }
}
