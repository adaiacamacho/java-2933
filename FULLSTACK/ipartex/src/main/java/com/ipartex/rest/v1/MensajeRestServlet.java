package com.ipartex.rest.v1;

import java.io.IOException;

import com.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
import bibliotecas.json.JsonHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/mensajes")
public class MensajeRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final JsonHelper JSON = (JsonHelper) ContenedorInyeccionDependencias.obtenerObjeto("jsonhelper");
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.anonimo");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().append(JSON.objetoAJson(NEGOCIO.listarMensajes()));
	}
}
