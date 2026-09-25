package bibliotecas.accesodatos;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public interface JdbcHelper<T> {

	Optional<T> ejecutarUnoSql(String sql, Function<ResultSet, T> mapper, Object... args);

	Collection<T> ejecutarSql(String sql, Function<ResultSet, T> mapper, Object... args);

}