package com.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartube.dtos.Comentario;
import com.ipartube.dtos.ComentarioInsertar;
import com.ipartube.dtos.ComentarioInsertarRespuesta;

import bibliotecas.accesodatos.BaseDeDatos;

public class ComentarioCrud {
	public static ArrayList<Comentario> obtenerTodosPorIdVideo(Long idVideo) {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM comentarios WHERE videos_id=?")) {
			pst.setLong(1, idVideo);
			ResultSet rs = pst.executeQuery();

			ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

			while (rs.next()) {
				Comentario comentario = new Comentario(rs.getLong("id"),
						rs.getTimestamp("fecha_hora").toLocalDateTime(), rs.getString("usuario"),
						rs.getString("texto"));
				comentarios.add(comentario);
			}

			return comentarios;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener los videos", e);
		}
	}

	public static ComentarioInsertarRespuesta insertar(ComentarioInsertar comentarioInsertar) {
		try (PreparedStatement pst = BaseDeDatos
				.crearSentencia("INSERT INTO comentarios (fecha_hora, usuario, texto, videos_id) VALUES (?,?,?,?)")) {
			pst.setObject(1, comentarioInsertar.fechaHora());
			pst.setString(2, comentarioInsertar.usuario());
			pst.setString(3, comentarioInsertar.texto());
			pst.setLong(4, comentarioInsertar.idVideo());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			Long id = rs.getLong(1);

			return new ComentarioInsertarRespuesta(id, comentarioInsertar.fechaHora(), comentarioInsertar.usuario(),
					comentarioInsertar.texto(), comentarioInsertar.idVideo());
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener los videos", e);
		}
	}
}
