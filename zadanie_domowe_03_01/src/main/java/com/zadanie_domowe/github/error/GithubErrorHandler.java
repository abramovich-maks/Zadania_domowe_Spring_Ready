package com.zadanie_domowe.github.error;

import com.zadanie_domowe.github.controller.GithubController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubController.class)
@Log4j2
public class GithubErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorUserResponseDto handleException(UserNotFoundException exception) {
        log.warn("UserNotFoundException while accessing user");
        return new ErrorUserResponseDto(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}