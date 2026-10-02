package bibliotecas.validaciones.jakarta;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import bibliotecas.validaciones.ValidadorErroresMapper;
import jakarta.validation.ConstraintViolation;

public class ValidadorErroresMapperJakarta implements ValidadorErroresMapper {

	@Override
    public Map<String, List<String>> mapear(
            Object errores,
            Class<?> claseObjeto) {

        if (!(errores instanceof Set<?> set)) {
            throw new IllegalArgumentException(
                    "Se esperaba un Set de ConstraintViolation"
            );
        }

        return set.stream()
                .map(ConstraintViolation.class::cast)
                .collect(Collectors.groupingBy(
                        violation ->
                                violation.getPropertyPath().toString(),
                        Collectors.mapping(
                                ConstraintViolation::getMessage,
                                Collectors.toList()
                        )
                ));
    }
}