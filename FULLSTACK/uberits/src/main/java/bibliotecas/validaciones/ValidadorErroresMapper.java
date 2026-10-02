package bibliotecas.validaciones;

import java.util.List;
import java.util.Map;

public interface ValidadorErroresMapper {
	Map<String, List<String>> mapear(Object errores, Class<?> claseObjeto);
}
