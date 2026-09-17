package com.ipartex.accesodatos.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.ipartex.accesodatos.AccesoDatosException;
import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.entidades.Mensaje;

public class DaoMensajeSqlite implements DaoMensaje {
	private static final String JDBC_URL = "jdbc:sqlite:ipartex.db";
	private static final String JDBC_USER = "";
	private static final String JDBC_PASS = "";

	private Collection<Mensaje> ejecutarConsultaSql(String sql, Object... args) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 1;

			for (Object arg : args) {
				pst.setObject(i++, arg);
			}

			Collection<Mensaje> mensajes = new ArrayList<>();

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Mensaje mensaje = new Mensaje(rs.getLong("id"), rs.getString("nombre"), rs.getString("texto"),
						LocalDateTime.parse(rs.getString("fecha_hora")));
				mensajes.add(mensaje);
			}

			return mensajes;
		} catch (SQLException e) {
			throw new AccesoDatosException("Fallo en la operación de base de datos", e);
		}
	}

	private Optional<Long> ejecutarCambioSql(String sql, Object... args) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			int i = 1;

			for (Object arg : args) {
				pst.setObject(i++, arg);
			}

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {
				return Optional.of(rs.getLong(1));
			}

			return Optional.empty();
		} catch (SQLException e) {
			throw new AccesoDatosException("Fallo en la operación de base de datos", e);
		}
	}

	@Override
	public Iterable<Mensaje> obtenerTodos() {
		return ejecutarConsultaSql("SELECT * FROM mensajes");
	}

	@Override
	public Iterable<Mensaje> obtenerParaPantalla() {
		return ejecutarConsultaSql("SELECT * FROM mensajes ORDER BY fecha_hora DESC");
	}

	@Override
	public Optional<Mensaje> obtenerPorId(Long id) {
		return ejecutarConsultaSql("SELECT * FROM mensajes WHERE id=?", id).stream().findFirst();
	}

	@Override
	public Mensaje insertar(Mensaje mensaje) {
		mensaje.setId(ejecutarCambioSql("INSERT INTO mensajes (nombre, texto, fecha_hora) VALUES (?,?,?)",
				mensaje.getNombre(), mensaje.getTexto(), mensaje.getFechaHora().toString()).get());

		return mensaje;
	}

	@Override
	public Mensaje modificar(Mensaje mensaje) {
		ejecutarCambioSql("UPDATE mensajes SET nombre=?, texto=?, fecha_hora=? WHERE id=?", mensaje.getNombre(),
				mensaje.getTexto(), mensaje.getFechaHora().toString(), mensaje.getId());

		return mensaje;
	}

	@Override
	public void borrar(Long id) {
		ejecutarCambioSql("DELETE FROM mensajes WHERE id=?", id);
	}

}
