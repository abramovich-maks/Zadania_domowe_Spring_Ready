package com.zadanie_domowe.github.domain.model;

public class RepositoryNotFoundException extends RuntimeException {
    public RepositoryNotFoundException(String message) {
        super(message);
    }
}
