package com.zadanie_domowe.github.controller;

import java.util.List;

public record GetReposByOwnerResponseDto (List<OwnerDto> repos) {
}
