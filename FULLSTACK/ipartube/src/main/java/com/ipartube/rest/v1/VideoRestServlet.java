package com.ipartube.rest.v1;

import com.ipartube.entidades.Video;
import com.ipartube.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;

@WebServlet("/api/v1/videos/*")
public class VideoRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final AnonimoNegocio ANONIMO_NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.anonimo");

	@GET
	public Iterable<Video> listarVideos(){
		return ANONIMO_NEGOCIO.listarVideos();
	}
	/**
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String[] partes = obtenerPartes(request);
		Long id = obtenerId(partes);

		PrintWriter out = response.getWriter();
		if (id != null) {
			if (partes.length == 2 && partes[1].equals("comentarios")) {
				out.append(GSON.toJson(ANONIMO_NEGOCIO.verComentariosVideo(id)));
				return;
			}

			out.append(GSON.toJson(ANONIMO_NEGOCIO.verDetalleVideo(id)));
			return;
		}

		out.append(GSON.toJson(ANONIMO_NEGOCIO.listarVideos()));
	}**/

	@POST
	public Response insertarVideo(Video video) {
		return null;
	}
	/**
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		VideoInsertarDto videoInsertar = GSON.fromJson(request.getReader(), VideoInsertarDto.class);

		VideoInsertarRespuestaDto videoInsertarRespuesta = ANONIMO_NEGOCIO.crearNuevoVideo(videoInsertar);

		response.getWriter().append(GSON.toJson(videoInsertarRespuesta));
	}**/
/**
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
	}**/
}
