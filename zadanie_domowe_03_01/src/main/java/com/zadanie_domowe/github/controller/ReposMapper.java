package com.zadanie_domowe.github.controller;

import com.zadanie_domowe.github.domain.model.RepoEntity;

import java.util.List;

public class ReposMapper {

    public static GetAllReposResponseDto mapFromRepoEntityToGetAllReposResponseDto(List<RepoEntity> allRepo) {
        List<ReposDto> reposDtos = allRepo.stream()
                .map(ReposMapper::getReposDto)
                .toList();
        return new GetAllReposResponseDto(reposDtos);
    }

    private static ReposDto getReposDto(RepoEntity repo) {
        return new ReposDto(repo.getId(), repo.getOwner(), repo.getName());
    }

    private static OwnerDto getOwnerDto(RepoEntity repo) {
        return new OwnerDto(repo.getId(), repo.getOwner(), repo.getName());
    }


    public static GetReposByOwnerResponseDto mapFromRepoEntityToGetReposByOwnerResponseDto(List<RepoEntity> repoByOwner) {
        List<OwnerDto> list = repoByOwner.stream()
                .map(ReposMapper::getOwnerDto)
                .toList();
        return new GetReposByOwnerResponseDto(list);
    }

}
