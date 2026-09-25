package bibliotecas.accesodatos;

import java.util.Optional;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class DaoJpa<T> implements Dao<T> {
	protected static final JpaHelper jpa = (JpaHelper) ContenedorInyeccionDependencias.obtenerObjeto("jpa.helper");
	
	private final Class<T> tipo;

    public DaoJpa(Class<T> tipo) {
        this.tipo = tipo;
    }
	
	@Override
	public Iterable<T> obtenerTodos() {
		return jpa.ejecutarJpa(em -> em.createQuery("from " + tipo.getName(), tipo).getResultList());
	}

	@Override
	public Optional<T> obtenerPorId(Long id) {
		return jpa.ejecutarJpa(em -> Optional.ofNullable(em.find(tipo, id)));
	}

	@Override
	public T insertar(T objeto) {
		return jpa.ejecutarJpa(em -> {
			em.persist(objeto);
			return objeto;
		});
	}

	@Override
	public T modificar(T objeto) {
		return jpa.ejecutarJpa(em -> {
			em.merge(objeto);
			return objeto;
		});
	}

	@Override
	public void borrar(Long id) {
		jpa.ejecutarJpa(em -> {
			em.remove(em.find(tipo, id));
			return null;
		});
	}

}
