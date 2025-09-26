package com.zadanie_domowe.github.controller;

import com.zadanie_domowe.github.controller.dto.request.CreateRequestDto;
import com.zadanie_domowe.github.controller.dto.response.*;
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

    public static DeleteReposResponseDto mapFromRepoEntityToDeleteReposResponseDto(Long id) {
        return new DeleteReposResponseDto("Repository with id: " + id + " have been deleted.", HttpStatus.OK);
    }

    public static DeleteAllReposByOwnerResponseDto mapFromRepoEntityToDeleteAllReposByOwnerResponseDto(String ownerName) {
        return new DeleteAllReposByOwnerResponseDto("All repository by owner: " + ownerName + " has been deleted.", HttpStatus.OK);
    }

    public static GetRepoByIdResponseDto mapFromRepoEntityToGetRepoByIdResponseDto(RepoEntity byId) {
        return new GetRepoByIdResponseDto(byId.getId(), byId.getOwner(), byId.getName());
    }

    public static RepoEntity mapToUpdateRepoRequestDtoFromRepoEntity(UpdateRepoRequestDto request) {
        return new RepoEntity(request.owner(), request.name());
    }

    public static UpdateRepoResponseDto mapToRepoEntityFromUpdateRepoResponseDto(RepoEntity newSong) {
        return new UpdateRepoResponseDto(newSong.getOwner(), newSong.getName());
    }
}
