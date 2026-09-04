package com.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.ipartube.dtos.Comentario;
import com.ipartube.dtos.ComentarioInsertar;
import com.ipartube.dtos.ComentarioInsertarRespuesta;

import bibliotecas.accesodatos.BaseDeDatos;

public class ComentarioCrud {
	public static ArrayList<Comentario> obtenerTodosPorIdVideo(Long idVideo) {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM vista_comentarios WHERE videos_id=?")) {
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
			throw new RuntimeException("Error al obtener los comentarios", e);
		}
	}

	public static ComentarioInsertarRespuesta insertar(ComentarioInsertar comentarioInsertar) {
		try (CallableStatement cst = BaseDeDatos
				.crearProcedimiento("call comentarios_insertar(?,?,?,?,?)")) {
			cst.registerOutParameter(1, Types.BIGINT);
			cst.setObject(2, comentarioInsertar.fechaHora());
			cst.setString(3, comentarioInsertar.texto());
			cst.setLong(4, comentarioInsertar.idVideo());
			cst.setLong(5, comentarioInsertar.idUsuario());

			cst.executeUpdate();

			Long id = cst.getLong(1);

			return new ComentarioInsertarRespuesta(id, comentarioInsertar.fechaHora(), comentarioInsertar.idUsuario(),
					comentarioInsertar.texto(), comentarioInsertar.idVideo());
		} catch (SQLException e) {
			throw new RuntimeException("Error al añadir un nuevo comentario " + comentarioInsertar, e);
		}
	}
}
