package com.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
		return new Video(1L, LocalDate.now(), "https://www.youtube.com/embed/fLexgOxsZu0", "Video de Bruno",
				"Descripción del video");
	}
}
