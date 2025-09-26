package com.zadanie_domowe.github.controller;

import org.springframework.http.HttpStatus;

public record DeleteReposResponseDto(String message, HttpStatus status) {
}
