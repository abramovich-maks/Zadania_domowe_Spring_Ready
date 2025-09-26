package com.zadanie_domowe.github.domain.service;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import com.zadanie_domowe.github.domain.model.RepositoryNotFoundException;
import com.zadanie_domowe.github.domain.repository.GithubRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RepoRetriever {

    private final GithubRepository githubRepository;


    public RepoRetriever(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public RepoEntity findById(Long id) {
        return githubRepository.findById(id)
                .orElseThrow(() -> new RepositoryNotFoundException("Repository with id: " + id + " not found"));
    }

    public List<RepoEntity> findAllRepo() {
        return githubRepository.findAll();
    }

    public List<RepoEntity> findRepoByOwner(String owner) {
        return githubRepository.findRepoByOwner(owner);
    }

    public void existById(Long id) {
        if (!githubRepository.existsById(id)) {
            throw new RepositoryNotFoundException("Repository with id: " + id + " not found");
        }
    }

    public void existByOwner(String owner) {
        if (!githubRepository.existsByOwner(owner)) {
            throw new RepositoryNotFoundException("Repository by owner: " + owner + " not found");
        }
    }
}
