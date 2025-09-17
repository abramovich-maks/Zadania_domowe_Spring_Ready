package com.zadanie_domowe.github.infrastructure.controller.dto.response;

import com.zadanie_domowe.github.domain.model.Commit;

public record BranchDTO(String name, Commit commit) {
}
