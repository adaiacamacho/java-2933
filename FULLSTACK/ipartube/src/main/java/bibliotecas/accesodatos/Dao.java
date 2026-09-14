package bibliotecas.accesodatos;

import java.util.ArrayList;

public interface Dao<T> {
	ArrayList<T> obtenerTodos();
	T obtenerPorId(Long id);
	
	T insertar(T o);
	T modificar(T o);
	void borrar(Long id);
}
