package com.zadanie_domowe.github.controller;

import com.zadanie_domowe.github.domain.model.RepoEntity;
import com.zadanie_domowe.github.domain.service.RepoRetriever;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.zadanie_domowe.github.controller.ReposMapper.*;

@RestController
@Log4j2
@RequestMapping("/owner")
public class OwnerCrudController {

    private final RepoRetriever repoRetriever;

    public OwnerCrudController(RepoRetriever repoRetriever) {
        this.repoRetriever = repoRetriever;
    }

    @GetMapping("/repos")
    public ResponseEntity<GetAllReposResponseDto> getAllRepo() {
        List<RepoEntity> allRepo = repoRetriever.findAllRepo();
        GetAllReposResponseDto responseDto = mapFromRepoEntityToGetAllReposResponseDto(allRepo);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{ownerName}")
    public ResponseEntity<GetReposByOwnerResponseDto> gerReposByOwner(@PathVariable(name = "ownerName") String owner) {
        List<RepoEntity> repoByOwner = repoRetriever.findRepoByOwner(owner);
        GetReposByOwnerResponseDto responseDto = mapFromRepoEntityToGetReposByOwnerResponseDto(repoByOwner);
        return ResponseEntity.ok(responseDto);
    }
}
