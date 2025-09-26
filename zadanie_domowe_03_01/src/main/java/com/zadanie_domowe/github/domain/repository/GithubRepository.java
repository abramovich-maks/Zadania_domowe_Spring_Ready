package com.zadanie_domowe.github.domain.repository;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GithubRepository extends Repository<RepoEntity, Long> {

    void save (RepoEntity owner);

    List<RepoEntity> findAll();
}
