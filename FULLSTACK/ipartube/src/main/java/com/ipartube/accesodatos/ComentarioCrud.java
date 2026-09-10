package com.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.ipartube.dtos.ComentarioDto;
import com.ipartube.dtos.ComentarioInsertarDto;
import com.ipartube.dtos.ComentarioInsertarRespuestaDto;

import bibliotecas.accesodatos.BaseDeDatos;

public class ComentarioCrud {
	public static ArrayList<ComentarioDto> obtenerTodosPorIdVideo(Long idVideo) {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM vista_comentarios WHERE videos_id=?")) {
			pst.setLong(1, idVideo);
			ResultSet rs = pst.executeQuery();

			ArrayList<ComentarioDto> comentarios = new ArrayList<ComentarioDto>();

			while (rs.next()) {
				ComentarioDto comentario = new ComentarioDto(rs.getLong("id"),
						rs.getTimestamp("fecha_hora").toLocalDateTime(), rs.getString("usuario"),
						rs.getString("texto"));
				comentarios.add(comentario);
			}

			return comentarios;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener los comentarios", e);
		}
	}

	public static ComentarioInsertarRespuestaDto insertar(ComentarioInsertarDto comentarioInsertar) {
		try (CallableStatement cst = BaseDeDatos
				.crearProcedimiento("call comentarios_insertar(?,?,?,?,?)")) {
			cst.registerOutParameter(1, Types.BIGINT);
			cst.setObject(2, comentarioInsertar.fechaHora());
			cst.setString(3, comentarioInsertar.texto());
			cst.setLong(4, comentarioInsertar.idVideo());
			cst.setLong(5, comentarioInsertar.idUsuario());

			cst.executeUpdate();

			Long id = cst.getLong(1);

			return new ComentarioInsertarRespuestaDto(id, comentarioInsertar.fechaHora(), comentarioInsertar.idUsuario(),
					comentarioInsertar.texto(), comentarioInsertar.idVideo());
		} catch (SQLException e) {
			throw new RuntimeException("Error al añadir un nuevo comentario " + comentarioInsertar, e);
		}
	}
}
