package com.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartube.dtos.Comentario;

import bibliotecas.accesodatos.BaseDeDatos;

public class ComentarioCrud {
	public static ArrayList<Comentario> obtenerTodosPorIdVideo(Long idVideo) {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM comentarios WHERE videos_id=?")) {
			pst.setLong(1, idVideo);
			ResultSet rs = pst.executeQuery();

			ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

			while (rs.next()) {
				Comentario comentario = new Comentario(rs.getLong("id"), rs.getTimestamp("fecha_hora").toLocalDateTime(),
						rs.getString("usuario"), rs.getString("texto"));
				comentarios.add(comentario);
			}

			return comentarios;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener los videos", e);
		}
	}
}
