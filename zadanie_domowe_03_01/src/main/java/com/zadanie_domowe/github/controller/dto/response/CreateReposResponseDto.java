package com.zadanie_domowe.github.controller.dto.response;

import org.springframework.http.HttpStatus;

public record CreateReposResponseDto(ReposResponseDto added, HttpStatus status) {
}
