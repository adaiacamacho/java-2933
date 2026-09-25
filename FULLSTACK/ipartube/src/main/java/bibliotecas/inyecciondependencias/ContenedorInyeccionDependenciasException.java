package bibliotecas.inyecciondependencias;

public class ContenedorInyeccionDependenciasException extends RuntimeException {
	private static final long serialVersionUID = 4385035755316422733L;

	public ContenedorInyeccionDependenciasException() {
		super();
	}

	public ContenedorInyeccionDependenciasException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ContenedorInyeccionDependenciasException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContenedorInyeccionDependenciasException(String message) {
		super(message);
	}

	public ContenedorInyeccionDependenciasException(Throwable cause) {
		super(cause);
	}

}
