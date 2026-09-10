package com.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.ipartube.dtos.VideoDto;
import com.ipartube.dtos.VideoInsertarDto;
import com.ipartube.dtos.VideoInsertarRespuestaDto;

import bibliotecas.accesodatos.BaseDeDatos;

public class VideoCrud {
	public static ArrayList<VideoDto> obtenerTodos() {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM videos");
				ResultSet rs = pst.executeQuery()) {
			ArrayList<VideoDto> videos = new ArrayList<VideoDto>();

			while (rs.next()) {
				VideoDto video = new VideoDto(rs.getLong("id"), rs.getDate("fecha").toLocalDate(), rs.getString("url"),
						rs.getString("titulo"), rs.getString("descripcion"));
				videos.add(video);
			}

			return videos;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener los videos", e);
		}
	}

	public static VideoDto obtenerPorId(Long id) {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM videos WHERE id=?")) {
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			VideoDto video = null;

			if (rs.next()) {
				video = new VideoDto(rs.getLong("id"), rs.getDate("fecha").toLocalDate(), rs.getString("url"),
						rs.getString("titulo"), rs.getString("descripcion"));
			}

			return video;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener el video " + id, e);
		}
	}

	public static VideoInsertarRespuestaDto insertar(VideoInsertarDto videoInsertar) {
		try (CallableStatement cst = BaseDeDatos.crearProcedimiento("call videos_insert(?,?,?,?,?,?)")) {
			cst.registerOutParameter(1, Types.BIGINT);
			cst.registerOutParameter(2, Types.DATE);
			
			cst.setString(3, videoInsertar.url());
			cst.setString(4, videoInsertar.titulo());
			cst.setString(5, videoInsertar.descripcion());
			cst.setLong(6, videoInsertar.idUsuario());

			cst.executeUpdate();

			return new VideoInsertarRespuestaDto(cst.getLong(1), cst.getDate(2).toLocalDate(), videoInsertar.url(),
					videoInsertar.titulo(), videoInsertar.descripcion(), videoInsertar.idUsuario());
		} catch (SQLException e) {
			throw new RuntimeException("Error al insertar el video " + videoInsertar, e);
		}
	}
}
