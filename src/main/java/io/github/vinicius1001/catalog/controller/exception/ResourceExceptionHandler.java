package io.github.vinicius1001.catalog.controller.exception;

import io.github.vinicius1001.catalog.service.exception.DataBaseException;
import io.github.vinicius1001.catalog.service.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.OffsetDateTime;

@ControllerAdvice
public class ResourceExceptionHandler  {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandarError> entityNotFound(EntityNotFoundException e, HttpServletRequest httpServletRequest){
        StandarError err = new StandarError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource Not Found");
        err.setMessage(e.getMessage());
        err.setPath(httpServletRequest.getRequestURI());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandarError> database(DataBaseException e, HttpServletRequest httpServletRequest){
        StandarError err = new StandarError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("DataBase Exception");
        err.setMessage(e.getMessage());
        err.setPath(httpServletRequest.getRequestURI());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
