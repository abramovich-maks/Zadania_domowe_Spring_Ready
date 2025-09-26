package com.zadanie_domowe.github.controller.dto;

import com.zadanie_domowe.github.proxy.Commit;

public record BranchDTO(String name, Commit commit) {
}
