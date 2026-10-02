package bibliotecas.validaciones;

import java.util.List;
import java.util.Map;

public class ValidadorException extends RuntimeException {
	private Map<String, List<String>> errores;
	
	public ValidadorException(Map<String, List<String>> errores) {
		this.errores = errores;
	}
	
	public ValidadorException() {
		super();
	}

	public ValidadorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidadorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidadorException(String message) {
		super(message);
	}

	public ValidadorException(Throwable cause) {
		super(cause);
	}

	public Map<String, List<String>> getErrores() {
		return errores;
	}

	private static final long serialVersionUID = -585523470132831073L;

}
