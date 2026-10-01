package bibliotecas.validaciones;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

public class ValidadorImpl implements Validador {
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Override
	public <T> void validar(T objeto) {
		Set<ConstraintViolation<T>> errores = validator.validate(objeto);

		if (!errores.isEmpty()) {
			throw new ValidationException();
		}
	}

}
