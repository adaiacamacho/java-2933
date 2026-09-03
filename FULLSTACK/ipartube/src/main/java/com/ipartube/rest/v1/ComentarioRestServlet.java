package com.ipartube.rest.v1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.ipartube.dtos.ComentarioInsertar;
import com.ipartube.dtos.ComentarioInsertarRespuesta;
import com.ipartube.dtos.ComentarioPost;
import com.ipartube.logicanegocio.AnonimoNegocio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/comentarios/*")
public class ComentarioRestServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		ComentarioPost comentarioPost = GSON.fromJson(request.getReader(), ComentarioPost.class);

		ComentarioInsertar comentarioInsertar = new ComentarioInsertar(LocalDateTime.now(), comentarioPost.usuario(),
				comentarioPost.texto(), comentarioPost.idVideo());

		ComentarioInsertarRespuesta comentarioInsertarRespuesta = AnonimoNegocio
				.crearNuevoComentario(comentarioInsertar);

		response.getWriter().append(GSON.toJson(comentarioInsertarRespuesta));
	}
}
