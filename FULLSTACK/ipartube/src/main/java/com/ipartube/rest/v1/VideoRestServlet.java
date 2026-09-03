package com.ipartube.rest.v1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.ipartube.logicanegocio.AnonimoNegocio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/videos/*")
public class VideoRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// @formatter:off
	private static final JsonSerializer<LocalDate> SER_LOCAL_DATE = 
			(src, type, ctx) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));

	private static final JsonDeserializer<LocalDate> DESER_LOCAL_DATE = 
			(json, type, ctx) -> LocalDate.parse(json.getAsString());

	private static final JsonSerializer<LocalDateTime> SER_LOCAL_DATE_TIME = 
			(src, type, ctx) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

	private static final JsonDeserializer<LocalDateTime> DESER_LOCAL_DATE_TIME = 
			(json, type, ctx) -> LocalDateTime.parse(json.getAsString());
	
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(LocalDate.class, SER_LOCAL_DATE)
			.registerTypeAdapter(LocalDate.class, DESER_LOCAL_DATE)
			.registerTypeAdapter(LocalDateTime.class, SER_LOCAL_DATE_TIME)
			.registerTypeAdapter(LocalDateTime.class, DESER_LOCAL_DATE_TIME)
		.create();
	// @formatter:on

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String[] partes = obtenerPartes(request);
		Long id = obtenerId(partes);

		PrintWriter out = response.getWriter();
		if (id != null) {
			if (partes.length == 2 && partes[1].equals("comentarios")) {
				out.append(GSON.toJson(AnonimoNegocio.verComentariosVideo(id)));
				return;
			}

			out.append(GSON.toJson(AnonimoNegocio.verDetalleVideo(id)));
			return;
		}

		out.append(GSON.toJson(AnonimoNegocio.listarVideos()));
	}

	private String[] obtenerPartes(HttpServletRequest request) {
		if (request.getPathInfo() == null)
			return new String[0];
		return request.getPathInfo().substring(1).split("/");
	}

	private Long obtenerId(String[] partes) {
		Long id = null;

		if (partes.length > 0 && partes[0].length() > 0) {
			id = Long.parseLong(partes[0]);
		}

		return id;
	}
}
