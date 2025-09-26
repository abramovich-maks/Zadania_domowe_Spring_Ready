package com.zadanie_domowe.github.controller;

import com.zadanie_domowe.github.controller.dto.request.CreateRequestDto;
import com.zadanie_domowe.github.controller.dto.response.CreateReposResponseDto;
import com.zadanie_domowe.github.controller.dto.response.GetAllReposResponseDto;
import com.zadanie_domowe.github.controller.dto.response.GetReposByOwnerResponseDto;
import com.zadanie_domowe.github.controller.dto.response.ReposResponseDto;
import com.zadanie_domowe.github.domain.model.RepoEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ReposMapper {

    public static GetAllReposResponseDto mapFromRepoEntityToGetAllReposResponseDto(List<RepoEntity> allRepo) {
        List<ReposResponseDto> reposResponseDtos = allRepo.stream()
                .map(ReposMapper::getReposDto)
                .toList();
        return new GetAllReposResponseDto(reposResponseDtos);
    }

    private static ReposResponseDto getReposDto(RepoEntity repo) {
        return new ReposResponseDto(repo.getId(), repo.getOwner(), repo.getName());
    }

    private static ReposResponseDto getOwnerDto(RepoEntity repo) {
        return new ReposResponseDto(repo.getId(), repo.getOwner(), repo.getName());
    }


    public static GetReposByOwnerResponseDto mapFromRepoEntityToGetReposByOwnerResponseDto(List<RepoEntity> repoByOwner) {
        List<ReposResponseDto> list = repoByOwner.stream()
                .map(ReposMapper::getOwnerDto)
                .toList();
        return new GetReposByOwnerResponseDto(list);
    }

    public static CreateReposResponseDto mapFromRepoEntityToCreateReposResponseDto(RepoEntity newRepo) {
        return new CreateReposResponseDto(getReposDto(newRepo), HttpStatus.CREATED);
    }

    public static RepoEntity mapFromCreateRequestDtoToRepoEntity(CreateRequestDto request) {
        return new RepoEntity(request.owner(), request.name());
    }
}
