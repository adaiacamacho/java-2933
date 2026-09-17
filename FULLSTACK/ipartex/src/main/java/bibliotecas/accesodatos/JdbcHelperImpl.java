package bibliotecas.accesodatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

public class JdbcHelperImpl<T> implements JdbcHelper<T> {
	private static final String JDBC_DRIVER;
	private static final String JDBC_URL;
	private static final String JDBC_USER;
	private static final String JDBC_PASS;

	static {
		try {
			Properties props = new Properties();
			props.load(JdbcHelperImpl.class.getClassLoader().getResourceAsStream("aplicacion.properties"));

			JDBC_DRIVER = props.getProperty("jdbc.driver");
			JDBC_URL = props.getProperty("jdbc.url");
			JDBC_USER = props.getProperty("jdbc.user");
			JDBC_PASS = props.getProperty("jdbc.pass");

			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver", e);
		} catch (IOException e) {
			throw new AccesoDatosException("No se ha podido abrir la configuración");
		}
	}
	
	@Override
	public Optional<T> ejecutarUnoSql(String sql, Function<ResultSet, T> mapper, Object... args) {
		return ejecutarSql(sql, mapper, args).stream().findFirst();
	}

	@Override
	public Collection<T> ejecutarSql(String sql, Function<ResultSet, T> mapper, Object... args) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 1;

			for (Object arg : args) {
				pst.setObject(i++, arg);
			}

			Collection<T> objetos = new ArrayList<>();

			if (pst.execute()) {
				ResultSet rs = pst.getResultSet();
				
				while (rs.next()) {
					objetos.add(mapper.apply(rs));
				}
			} else {
				ResultSet rs = pst.getGeneratedKeys();

				if (rs.next()) {
					objetos.add(mapper.apply(rs));
				}
			}

			return objetos;
		} catch (SQLException e) {
			throw new AccesoDatosException("Fallo en la operación de base de datos", e);
		}
	}
}
