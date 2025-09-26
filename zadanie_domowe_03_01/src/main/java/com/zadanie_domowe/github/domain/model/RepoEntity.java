package com.zadanie_domowe.github.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "repo")
@Getter
public class RepoEntity {

    public RepoEntity() {
    }

    public RepoEntity(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String owner;

    private String name;


}
