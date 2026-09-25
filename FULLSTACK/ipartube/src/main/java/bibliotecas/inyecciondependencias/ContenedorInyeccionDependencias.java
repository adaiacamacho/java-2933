package bibliotecas.inyecciondependencias;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import bibliotecas.accesodatos.AccesoDatosException;
import bibliotecas.accesodatos.JdbcHelperImpl;

public class ContenedorInyeccionDependencias {
	private static final Properties props = new Properties();
	
	static {
		try {
			props.load(JdbcHelperImpl.class.getClassLoader().getResourceAsStream("aplicacion.properties"));
		} catch (IOException e) {
			throw new AccesoDatosException("No se ha podido abrir la configuración", e);
		}
	}

	private ContenedorInyeccionDependencias() {
	}

	public static Object obtenerObjeto(String propiedad) {
		try {
			String nombreClase = props.getProperty(propiedad);

			Class<?> clase = Class.forName(nombreClase);
			Constructor<?> constructor = clase.getConstructor();

			return constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ContenedorInyeccionDependenciasException("No se ha podido crear el objeto", e);
		}
	}
}
