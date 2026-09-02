package com.ipartube.rest.v1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.ipartube.dtos.Video;
import com.ipartube.logicanegocio.AnonimoNegocio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/videos/*")
public class VideoRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final JsonSerializer<LocalDate> SER = (src, type,
			ctx) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));

	private static final JsonDeserializer<LocalDate> DESER = (json, type, ctx) -> LocalDate.parse(json.getAsString());

	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDate.class, SER)
			.registerTypeAdapter(LocalDate.class, DESER).create();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		Long id = obtenerId(request);

		if (id != null) {
			response.getWriter().append(GSON.toJson(new Video(1L, LocalDate.now(),
					"https://www.youtube.com/embed/fLexgOxsZu0", "Video de Bruno", "Descripción del video")));
			return;
		}

		response.getWriter().append(GSON.toJson(AnonimoNegocio.listarVideos()));
	}

	private Long obtenerId(HttpServletRequest request) {
		Long id = null;

		String pathInfo = request.getPathInfo();

		if (pathInfo != null && !pathInfo.equals("/")) {
			id = Long.parseLong(pathInfo.substring(1));
		}
		
		return id;
	}
}
