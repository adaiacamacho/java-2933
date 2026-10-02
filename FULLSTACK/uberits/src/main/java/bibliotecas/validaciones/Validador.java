package bibliotecas.validaciones;

public interface Validador {
	<T> void validar(T objeto, Class<T> clase);
}
