package com.zadanie_domowe.github.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "repo")
@Getter
@Setter
@Builder
public class RepoEntity {

    public RepoEntity() {
    }

    public RepoEntity(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public RepoEntity(Long id, String owner, String name) {
        this.id = id;
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
