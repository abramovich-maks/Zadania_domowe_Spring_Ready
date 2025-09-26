package com.zadanie_domowe.github.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorReposResponseDto(String message, HttpStatus status) {
}
