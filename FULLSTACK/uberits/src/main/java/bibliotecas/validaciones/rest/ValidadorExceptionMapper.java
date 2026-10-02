package bibliotecas.validaciones.rest;

import java.util.List;
import java.util.Map;

import bibliotecas.validaciones.ValidadorException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;;

@Provider
public class ValidadorExceptionMapper
        implements ExceptionMapper<ValidadorException> {

    @Override
    public Response toResponse(ValidadorException exception) {

        Map<String, List<String>> errors = exception.getErrores();

        ProblemResponse response = new ProblemResponse(
                "https://uberits.com/problemas/error-validacion",
                "Fallo de validación",
                400,
                "Hay al menos un campo con errores.",
                errors
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .type("application/problem+json")
                .entity(response)
                .build();
    }
}
