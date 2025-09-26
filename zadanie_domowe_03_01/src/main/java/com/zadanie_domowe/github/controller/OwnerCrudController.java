package com.zadanie_domowe.github.controller;

import com.zadanie_domowe.github.controller.dto.request.CreateRequestDto;
import com.zadanie_domowe.github.controller.dto.response.CreateReposResponseDto;
import com.zadanie_domowe.github.controller.dto.response.GetAllReposResponseDto;
import com.zadanie_domowe.github.controller.dto.response.GetReposByOwnerResponseDto;
import com.zadanie_domowe.github.domain.model.RepoEntity;
import com.zadanie_domowe.github.domain.service.RepoAdder;
import com.zadanie_domowe.github.domain.service.RepoRetriever;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zadanie_domowe.github.controller.ReposMapper.*;

@RestController
@Log4j2
@RequestMapping("/owner")
public class OwnerCrudController {

    private final RepoRetriever repoRetriever;
    private final RepoAdder repoAdder;

    public OwnerCrudController(RepoRetriever repoRetriever, RepoAdder repoAdder) {
        this.repoRetriever = repoRetriever;
        this.repoAdder = repoAdder;
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

    @PostMapping
    public ResponseEntity<CreateReposResponseDto> addRepos(@RequestBody CreateRequestDto request) {
        RepoEntity entity = mapFromCreateRequestDtoToRepoEntity(request);
        RepoEntity saveRepo = repoAdder.addRepo(entity);
        CreateReposResponseDto createReposResponseDto = mapFromRepoEntityToCreateReposResponseDto(saveRepo);
        return ResponseEntity.ok(createReposResponseDto);
    }
}
