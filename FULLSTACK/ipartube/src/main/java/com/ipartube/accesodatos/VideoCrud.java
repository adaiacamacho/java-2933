package com.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.ipartube.entidades.Video;

import bibliotecas.accesodatos.BaseDeDatos;

public class VideoCrud {
	// TODO: Añadir relación con Usuario
	public static ArrayList<Video> obtenerTodos() {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM videos");
				ResultSet rs = pst.executeQuery()) {
			ArrayList<Video> videos = new ArrayList<Video>();

			while (rs.next()) {
				Video video = new Video(rs.getLong("id"), rs.getDate("fecha").toLocalDate(), rs.getString("url"),
						rs.getString("titulo"), rs.getString("descripcion"));
				videos.add(video);
			}

			return videos;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener los videos", e);
		}
	}

	// TODO: Añadir relación con Usuario
	public static Video obtenerPorId(Long id) {
		try (PreparedStatement pst = BaseDeDatos.crearSentencia("SELECT * FROM videos WHERE id=?")) {
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Video video = null;

			if (rs.next()) {
				video = new Video(rs.getLong("id"), rs.getDate("fecha").toLocalDate(), rs.getString("url"),
						rs.getString("titulo"), rs.getString("descripcion"));
			}

			return video;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener el video " + id, e);
		}
	}

	public static Video insertar(Video video) {
		try (CallableStatement cst = BaseDeDatos.crearProcedimiento("call videos_insert(?,?,?,?,?,?)")) {
			cst.registerOutParameter(1, Types.BIGINT);
			cst.registerOutParameter(2, Types.DATE);
			
			cst.setString(3, video.getUrl());
			cst.setString(4, video.getTitulo());
			cst.setString(5, video.getDescripcion());
			cst.setLong(6, video.getUsuario().getId());

			cst.executeUpdate();
			
			video.setId(cst.getLong(1));
			video.setFecha(cst.getDate(2).toLocalDate());

			return video;
		} catch (SQLException e) {
			throw new RuntimeException("Error al insertar el video " + video, e);
		}
	}
}
