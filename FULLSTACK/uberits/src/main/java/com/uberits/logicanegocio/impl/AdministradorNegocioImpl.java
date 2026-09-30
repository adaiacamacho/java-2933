package com.uberits.logicanegocio.impl;

import com.uberits.accesodatos.DaoRestaurante;
import com.uberits.entidades.Restaurante;
import com.uberits.logicanegocio.AdministradorNegocio;

public class AdministradorNegocioImpl implements AdministradorNegocio{
	private DaoRestaurante daoRestaurante;
	
	
	public AdministradorNegocioImpl(DaoRestaurante daoRestaurante) {
		this.daoRestaurante = daoRestaurante;
	}
	
	@Override
	public Restaurante crearRestaurante(Restaurante restaurante) {
		return daoRestaurante.insertar(restaurante);
	}

}
