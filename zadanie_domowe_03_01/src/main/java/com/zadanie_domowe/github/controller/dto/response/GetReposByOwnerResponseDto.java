package com.zadanie_domowe.github.controller.dto.response;

import java.util.List;

public record GetReposByOwnerResponseDto (List<ReposResponseDto> repos) {
}
