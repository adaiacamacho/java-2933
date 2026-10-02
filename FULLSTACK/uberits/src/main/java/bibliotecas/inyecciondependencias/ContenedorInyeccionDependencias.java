package bibliotecas.inyecciondependencias;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

import bibliotecas.accesodatos.AccesoDatosException;

public class ContenedorInyeccionDependencias {
	private static final Properties props = new Properties();

	static {
		try {
			props.load(ContenedorInyeccionDependencias.class.getClassLoader()
					.getResourceAsStream("aplicacion.properties"));
		} catch (IOException e) {
			throw new AccesoDatosException("No se ha podido abrir la configuración", e);
		}
	}

	private ContenedorInyeccionDependencias() {
	}

	public static Object obtenerObjeto(String propiedad) {
		String nombreClase = null;
		Class<?> clase = null;
		Constructor<?> constructor = null;

		try {
			nombreClase = props.getProperty(propiedad);

			clase = Class.forName(nombreClase);
			constructor = clase.getConstructor();

			return constructor.newInstance();
		} catch (Exception e) {
			throw new ContenedorInyeccionDependenciasException(String.format("""
					No se ha podido crear el objeto.
					propiedad: %s
					nombreClase: %s
					clase: %s
					constructor: %s
					""", propiedad, nombreClase, clase, constructor), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T obtenerObjeto(String propiedad, Class<T> clase) {
		return (T) obtenerObjeto(propiedad);
	}
}
