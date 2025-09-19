package com.zadanie_domowe.github.controller;

import com.zadanie_domowe.github.proxy.Commit;

public record BranchDTO(String name, Commit commit) {
}
