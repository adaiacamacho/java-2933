package bibliotecas.json;

import java.io.Reader;

public interface JsonHelper {
	String objetoAJson(Object o);
	<T> T objetoDesdeJson(String json, Class<T> clase);
	<T> T objetoDesdeJson(Reader reader, Class<T> clase);
}
