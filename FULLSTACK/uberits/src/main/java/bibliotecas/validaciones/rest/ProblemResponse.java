package bibliotecas.validaciones.rest;

import java.util.List;
import java.util.Map;

public record ProblemResponse(String type, String title, int status, String detail, Map<String, List<String>> errors) {

}
