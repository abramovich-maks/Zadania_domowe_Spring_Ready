package com.zadanie_domowe.github.error;

import org.springframework.http.HttpStatus;

public record ErrorUserResponseDto(HttpStatus status, String message) {
}
