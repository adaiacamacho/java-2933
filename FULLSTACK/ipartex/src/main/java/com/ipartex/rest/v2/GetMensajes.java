package com.ipartex.rest.v2;

import java.util.function.Function;

import com.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.controladorfrontal.EntradaControladorFrontal;
import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class GetMensajes implements Function<EntradaControladorFrontal, Object> {
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.anonimo");

	@Override
	public Object apply(EntradaControladorFrontal entrada) {
		return NEGOCIO.listarMensajes();
	}

}
