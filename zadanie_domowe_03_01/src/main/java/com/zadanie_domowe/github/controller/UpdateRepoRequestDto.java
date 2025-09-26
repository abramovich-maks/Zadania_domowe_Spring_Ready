package com.zadanie_domowe.github.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRepoRequestDto(
        @NotNull(message = "owner must not be null")
        @NotEmpty(message = "owner must not be empty")
        String owner,
        @NotNull(message = "name must not be null")
        @NotEmpty(message = "name must not be empty")
        String name) {
}
