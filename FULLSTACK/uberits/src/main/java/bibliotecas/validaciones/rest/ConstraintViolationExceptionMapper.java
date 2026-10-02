package bibliotecas.validaciones.rest;

import static com.uberits.config.ContenedorDependencias.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;;

@Provider
public class ConstraintViolationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        Map<String, List<String>> errors = VALIDADOR_MAPPER.mapear(exception.getConstraintViolations(), Set.class);

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
