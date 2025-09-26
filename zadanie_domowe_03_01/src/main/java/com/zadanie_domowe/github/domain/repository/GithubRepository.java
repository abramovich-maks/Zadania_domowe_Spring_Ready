package com.zadanie_domowe.github.domain.repository;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GithubRepository extends Repository<RepoEntity, Long> {

    RepoEntity save(RepoEntity owner);

    List<RepoEntity> findAll();

    List<RepoEntity> findRepoByOwner(String owner);

    @Modifying
    @Query("delete from RepoEntity r where r.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("delete from RepoEntity r where r.owner = :owner")
    void deleteAllByOwner(String owner);

    boolean existsById(Long id);

    boolean existsByOwner(String owner);
}
