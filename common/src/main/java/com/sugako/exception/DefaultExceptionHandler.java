package com.sugako.exception;


import com.sugako.util.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static com.sugako.exception.response.ApplicationErrorCodes.BAD_REQUEST;
import static com.sugako.exception.response.ApplicationErrorCodes.ENTITY_NOT_FOUND;
import static com.sugako.exception.response.ApplicationErrorCodes.FATAL_ERROR;
import static com.sugako.exception.response.ApplicationErrorCodes.INVENTORY_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {

    private static final Logger log = Logger.getLogger(DefaultExceptionHandler.class);

    private final RandomValuesGenerator generator;

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<ErrorMessage> handleIllegalRequestException(IllegalRequestException e) {

        String exceptionUniqueId = generator.uuidGenerator();

        BindingResult bindingResult = e.getBindingResult();
        String collect = bindingResult
                .getAllErrors()
                .stream()
                .map(ObjectError::toString)
                .collect(Collectors.joining(","));

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        BAD_REQUEST.getCodeId(),
                        collect),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOtherException(Exception e) {

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        FATAL_ERROR.getCodeId(),
                        e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException e) {

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        ENTITY_NOT_FOUND.getCodeId(),
                        e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException e) {

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        INVENTORY_ERROR.getCodeId(),
                        e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
