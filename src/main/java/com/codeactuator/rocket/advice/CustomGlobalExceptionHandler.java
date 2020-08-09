package com.codeactuator.rocket.advice;

import com.codeactuator.rocket.error.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ProjectNotFoundException.class})
    public void entityNotFoundException(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(HttpStatus.NOT_FOUND.value());
    }
}
