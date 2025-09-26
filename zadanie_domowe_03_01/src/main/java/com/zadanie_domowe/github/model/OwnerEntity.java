package com.zadanie_domowe.github.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "repo")
@Getter
public class OwnerEntity {

    public OwnerEntity() {
    }

    public OwnerEntity(String owner, String name) {
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
