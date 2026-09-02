package com.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartube.dtos.Video;

import bibliotecas.accesodatos.BaseDeDatos;

public class VideoCrud {
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
}
