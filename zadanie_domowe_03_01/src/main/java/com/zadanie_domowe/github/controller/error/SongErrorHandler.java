package com.zadanie_domowe.github.controller.error;

import com.zadanie_domowe.github.controller.OwnerCrudController;
import com.zadanie_domowe.github.domain.model.RepositoryNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = OwnerCrudController.class)
@Log4j2
public class SongErrorHandler {

    @ExceptionHandler(RepositoryNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorReposResponseDto handleException(RepositoryNotFoundException exception) {
        log.warn("RepositoryNotFoundException while accessing repository");
        return new ErrorReposResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}