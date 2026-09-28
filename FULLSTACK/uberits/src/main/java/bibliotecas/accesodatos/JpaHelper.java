package bibliotecas.accesodatos;

import java.util.function.Function;

import jakarta.persistence.EntityManager;

public interface JpaHelper {

	<T> T ejecutarJpa(Function<EntityManager, T> sentencias);

}