package com.ipartube.rest.v1;

import com.ipartube.entidades.Comentario;
import com.ipartube.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;

@WebServlet("/api/v1/comentarios/*")
public class ComentarioRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final AnonimoNegocio ANONIMO_NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.anonimo");

	
	@POST
	public Response insertarComentario(Comentario comentario) {
		return Response.created(null).entity(ANONIMO_NEGOCIO.crearNuevoComentario(comentario)).build();
	}
}
