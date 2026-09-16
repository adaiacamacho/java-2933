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

	@Override
	public Iterable<Mensaje> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM mensajes");
				ResultSet rs = pst.executeQuery()) {
			Collection<Mensaje> mensajes = new ArrayList<>();

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

	@Override
	public Iterable<Mensaje> obtenerParaPantalla() {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM mensajes ORDER BY fecha_hora DESC");
				ResultSet rs = pst.executeQuery()) {
			Collection<Mensaje> mensajes = new ArrayList<>();

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

	@Override
	public Optional<Mensaje> obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM mensajes WHERE id=?");) {
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return Optional.of(new Mensaje(rs.getLong("id"), rs.getString("nombre"), rs.getString("texto"),
						LocalDateTime.parse(rs.getString("fecha_hora"))));
			}

			return Optional.empty();
		} catch (SQLException e) {
			throw new AccesoDatosException("Fallo en la operación de base de datos", e);
		}

	}

	@Override
	public Mensaje insertar(Mensaje mensaje) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(
						"INSERT INTO mensajes (nombre, texto, fecha_hora) VALUES (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, mensaje.getNombre());
			pst.setString(2, mensaje.getTexto());
			pst.setString(3, mensaje.getFechaHora().toString());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();

			rs.next();

			mensaje.setId(rs.getLong(1));

			return mensaje;
		} catch (SQLException e) {
			throw new AccesoDatosException("Fallo en la operación de base de datos", e);
		}
	}

	@Override
	public Mensaje modificar(Mensaje mensaje) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con
						.prepareStatement("UPDATE mensajes SET nombre=?, texto=?, fecha_hora=? WHERE id=?");) {
			pst.setString(1, mensaje.getNombre());
			pst.setString(2, mensaje.getTexto());
			pst.setString(3, mensaje.getFechaHora().toString());
			pst.setLong(4, mensaje.getId());

			pst.executeUpdate();

			return mensaje;
		} catch (SQLException e) {
			throw new AccesoDatosException("Fallo en la operación de base de datos", e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement("DELETE FROM mensajes WHERE id=?");) {
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("Fallo en la operación de base de datos", e);
		}
	}

}
