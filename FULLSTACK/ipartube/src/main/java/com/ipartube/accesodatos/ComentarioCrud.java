package com.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.ipartube.dtos.ComentarioDto;
import com.ipartube.entidades.Comentario;

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

	public static Comentario insertar(Comentario comentario) {
		try (CallableStatement cst = BaseDeDatos
				.crearProcedimiento("call comentarios_insertar(?,?,?,?,?)")) {
			cst.registerOutParameter(1, Types.BIGINT);
			cst.setObject(2, comentario.getFechaHora());
			cst.setString(3, comentario.getTexto());
			cst.setLong(4, comentario.getVideo().getId());
			cst.setLong(5, comentario.getUsuario().getId());

			cst.executeUpdate();

			comentario.setId(cst.getLong(1));

			return comentario;
		} catch (SQLException e) {
			throw new RuntimeException("Error al añadir un nuevo comentario " + comentario, e);
		}
	}
}
