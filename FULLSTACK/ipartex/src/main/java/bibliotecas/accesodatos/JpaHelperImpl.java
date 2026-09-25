package bibliotecas.accesodatos;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaHelperImpl implements JpaHelper {
	private static final String UNIDAD_PERSISTENCIA;

	static {
		try {
			Properties props = new Properties();
			props.load(JpaHelperImpl.class.getClassLoader().getResourceAsStream("aplicacion.properties"));

			UNIDAD_PERSISTENCIA = props.getProperty("jpa.unidadpersistencia");
		} catch (IOException e) {
			throw new AccesoDatosException("No se ha podido abrir la configuración", e);
		}
	}

	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(UNIDAD_PERSISTENCIA);

	@Override
	public <T> T ejecutarJpa(Function<EntityManager, T> sentencias) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			try {
				t.begin();

				T resultado = sentencias.apply(em);

				t.commit();

				return resultado;
			} catch (Exception e) {
				if (t != null && t.isActive()) {
					t.rollback();
				}

				throw new AccesoDatosException("Error en la operación de persistencia", e);
			}
		}
	}
}
