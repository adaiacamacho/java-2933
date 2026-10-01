package com.uberits.logicanegocio.impl;

import java.util.Optional;
import java.util.logging.Logger;

import com.uberits.accesodatos.DaoRestaurante;
import com.uberits.accesodatos.DaoUsuario;
import com.uberits.entidades.Restaurante;
import com.uberits.entidades.Usuario;
import com.uberits.logicanegocio.AdministradorNegocio;

import bibliotecas.validaciones.Validador;

public class AdministradorNegocioImpl implements AdministradorNegocio{
	private static final Logger log = Logger.getLogger(AdministradorNegocio.class.getName());
	private DaoRestaurante daoRestaurante;
	private DaoUsuario daoUsuario;
	private Validador validador;
	
	public AdministradorNegocioImpl(DaoRestaurante daoRestaurante, DaoUsuario daoUsuario, Validador validador) {
		this.daoRestaurante = daoRestaurante;
		this.daoUsuario = daoUsuario;
		this.validador = validador;
	}
	
	@Override
	public Restaurante crearRestaurante(Restaurante restaurante) {
		log.info("Se ha creado un restaurante");
		validador.validar(restaurante);
		return daoRestaurante.insertar(restaurante);
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		log.info("Se ha obtenido un usuario por id");
		return daoUsuario.obtenerPorId(id);
	}

}
