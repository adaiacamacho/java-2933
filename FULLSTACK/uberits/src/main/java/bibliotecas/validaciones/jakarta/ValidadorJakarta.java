package bibliotecas.validaciones.jakarta;

import static com.uberits.config.ContenedorDependencias.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import bibliotecas.validaciones.Validador;
import bibliotecas.validaciones.ValidadorException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ValidadorJakarta implements Validador {
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Override
	public <T> void validar(T objeto, Class<T> clase) {
		Map<String, List<String>> errores = VALIDADOR_MAPPER.mapear(validator.validate(objeto), Set.class);

		if (!errores.isEmpty()) {
			throw new ValidadorException(errores);
		}
	}

}
