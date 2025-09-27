package com.zadanie_domowe.github.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateRequestDto(
        @NotNull(message = "owner must not be null")
        @NotEmpty(message = "owner must not be empty")
        @NotBlank(message = "owner must not be blank")
        String owner,
        @NotNull(message = "name must not be null")
        @NotEmpty(message = "name must not be empty")
        @NotBlank(message = "name must not be blank")
        String name) {
}
