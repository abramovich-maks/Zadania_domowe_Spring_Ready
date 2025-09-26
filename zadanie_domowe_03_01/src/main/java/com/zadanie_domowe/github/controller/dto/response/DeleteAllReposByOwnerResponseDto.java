package com.zadanie_domowe.github.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteAllReposByOwnerResponseDto(String message, HttpStatus status) {
}
