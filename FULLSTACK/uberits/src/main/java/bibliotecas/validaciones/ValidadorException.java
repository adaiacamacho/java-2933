package bibliotecas.validaciones;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidadorException extends RuntimeException {
	
	private final Map<String, List<String>> errores;
	
	private static final long serialVersionUID = -585523470132831073L;

}
