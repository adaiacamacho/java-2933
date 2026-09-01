package com.ipartube.rest.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;
import com.ipartube.logicanegocio.AnonimoNegocio;

@WebServlet("/videos/*")
public class VideoRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Gson GSON = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().append(GSON.toJson(AnonimoNegocio.listarVideos()));
	}
}
