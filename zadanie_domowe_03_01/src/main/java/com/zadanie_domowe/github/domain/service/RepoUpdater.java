package com.zadanie_domowe.github.domain.service;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import com.zadanie_domowe.github.domain.repository.GithubRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j2
public class RepoUpdater {

    private final GithubRepository githubRepository;
    private final RepoRetriever repoRetriever;

    public RepoUpdater(GithubRepository githubRepository, RepoRetriever repoRetriever) {
        this.githubRepository = githubRepository;
        this.repoRetriever = repoRetriever;
    }

    public void updateById(Long id, RepoEntity newRepo) {
        repoRetriever.existById(id);
        githubRepository.updateById(id, newRepo);
    }

    public RepoEntity updatePartiallyById(Long id, RepoEntity repoFromRequest) {
        RepoEntity repoFromDatabase = repoRetriever.findById(id);
        RepoEntity.RepoEntityBuilder builder = RepoEntity.builder();
        if (repoFromRequest.getOwner() != null) {
            builder.owner(repoFromRequest.getOwner());
            log.info("partially updated repo (old owner: \"{}\") with id: {}", repoFromDatabase.getOwner(), id);
        } else {
            builder.owner(repoFromDatabase.getOwner());
        }
        if (repoFromRequest.getName() != null) {
            builder.name(repoFromRequest.getName());
            log.info("partially updated repo (old repository: \"{}\") with id: {}", repoFromDatabase.getName(), id);
        } else {
            builder.name(repoFromDatabase.getName());
        }
        builder.id(id);
        RepoEntity toSave = builder.build();
        updateById(id, toSave);
        return toSave;
    }
}
