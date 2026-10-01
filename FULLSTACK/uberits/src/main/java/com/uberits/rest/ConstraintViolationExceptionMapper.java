package com.uberits.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        Map<String, List<String>> errors =
                exception.getConstraintViolations()
                        .stream()
                        .collect(Collectors.groupingBy(
                                violation -> violation.getPropertyPath().toString(),
                                Collectors.mapping(
                                        ConstraintViolation::getMessage,
                                        Collectors.toList()
                                )
                        ));

        ProblemResponse response = new ProblemResponse(
                "https://example.com/problems/validation-error",
                "Validation failed",
                400,
                "One or more fields are invalid.",
                errors
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .type("application/problem+json")
                .entity(response)
                .build();
    }
}
