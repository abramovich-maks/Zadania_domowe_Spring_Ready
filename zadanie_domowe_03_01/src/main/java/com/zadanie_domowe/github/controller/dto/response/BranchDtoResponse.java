package com.zadanie_domowe.github.controller.dto.response;

import com.zadanie_domowe.github.proxy.Commit;

public record BranchDtoResponse(String name, Commit commit) {
}
