package com.zadanie_domowe.github.controller;

import org.springframework.http.HttpStatus;

public record DeleteAllReposByOwnerResponseDto(String message, HttpStatus status) {
}
