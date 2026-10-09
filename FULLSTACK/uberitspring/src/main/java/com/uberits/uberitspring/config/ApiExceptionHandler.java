package com.uberits.uberitspring.config;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ProblemDetail problem = ex.getBody();
        problem.setDetail("Error de validación");

        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        org.springframework.validation.FieldError::getField,
                        java.util.LinkedHashMap::new,
                        java.util.stream.Collectors.mapping(
                                error -> error.getDefaultMessage() != null
                                        ? error.getDefaultMessage()
                                        : "Valor no válido",
                                java.util.stream.Collectors.toList()
                        )
                ));

        problem.setProperty("errors", errors);

        return handleExceptionInternal(
                ex, problem, headers, status, request);
    }
}