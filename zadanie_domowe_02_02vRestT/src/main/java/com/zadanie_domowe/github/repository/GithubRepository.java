package com.zadanie_domowe.github.repository;

import com.zadanie_domowe.github.RepoEntity;
import org.springframework.data.repository.Repository;

public interface GithubRepository extends Repository<RepoEntity,Long> {

    RepoEntity save(RepoEntity entity);
}
