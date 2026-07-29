package com.amazonia.rest.v1;

import java.io.IOException;

import com.amazonia.dtos.Cliente;
import com.amazonia.dtos.Factura;
import com.amazonia.logicanegocio.ClienteNegocio;
import com.amazonia.presentacion.modelos.Carrito;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/facturas/*")
public class FacturaRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Gson GSON = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recibir información de la petición
		// 2. Convertir los datos
		// 3. Crear un objeto con ellos
		// 4. Llamar a la lógica de negocio
		// 5. Convertir a JSON
		// 6. Devolver el resultado
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recibir información de la petición
		String sIdCliente = request.getParameter("idCliente");
		Carrito carrito = GSON.fromJson(request.getReader(), Carrito.class);
		
		// 2. Convertir los datos
		Long idCliente = Long.parseLong(sIdCliente);
		
		// 3. Crear un objeto con ellos
		Cliente cliente = new Cliente(idCliente, null, null, null);
		
		// 4. Llamar a la lógica de negocio
		System.out.println(cliente);
		System.out.println(carrito);
		
		Factura factura = ClienteNegocio.facturar(cliente, carrito);
		
		// 5. Convertir a JSON
		String json = GSON.toJson(factura);
		
		// 6. Devolver el resultado
		response.setContentType("application/json");
		response.getWriter().append(json);
	}
}

















