package bibliotecas.accesodatos;

import java.util.Optional;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DaoJpa<T> implements Dao<T> {
	protected static JpaHelper JPA;
	
	static {
		try {
			JPA = ContenedorInyeccionDependencias.obtenerObjeto("jpa.helper", JpaHelper.class);
		} catch (Exception e) {
			JPA = new JpaHelperImpl();
		}
	}
	
	private final Class<T> tipo;
	
	@Override
	public Iterable<T> obtenerTodos() {
		return JPA.ejecutarJpa(em -> em.createQuery("from " + tipo.getName(), tipo).getResultList());
	}

	@Override
	public Optional<T> obtenerPorId(Long id) {
		return JPA.ejecutarJpa(em -> Optional.ofNullable(em.find(tipo, id)));
	}

	@Override
	public T insertar(T objeto) {
		return JPA.ejecutarJpa(em -> {
			em.persist(objeto);
			return objeto;
		});
	}

	@Override
	public T modificar(T objeto) {
		return JPA.ejecutarJpa(em -> {
			em.merge(objeto);
			return objeto;
		});
	}

	@Override
	public void borrar(Long id) {
		JPA.ejecutarJpa(em -> {
			em.remove(em.find(tipo, id));
			return null;
		});
	}

}
