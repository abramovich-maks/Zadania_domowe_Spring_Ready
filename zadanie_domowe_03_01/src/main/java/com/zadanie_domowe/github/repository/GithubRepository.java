package com.zadanie_domowe.github.repository;

import com.zadanie_domowe.github.model.OwnerEntity;
import org.springframework.data.repository.Repository;

public interface GithubRepository extends Repository<OwnerEntity, Long> {

    void save (OwnerEntity owner);
}
