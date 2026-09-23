package com.ipartex.logicanegocio.impl;

import java.util.Optional;
import java.util.logging.Logger;

import com.ipartex.accesodatos.DaoUsuario;
import com.ipartex.entidades.Usuario;
import com.ipartex.logicanegocio.AdministradorNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class AdministradorNegocioImpl implements AdministradorNegocio {
	private static final Logger log = Logger.getLogger(AdministradorNegocio.class.getName());
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) ContenedorInyeccionDependencias
			.obtenerObjeto("dao.usuario");

	@Override
	public Iterable<Usuario> obtenerListadoUsuarios() {
		log.info("Se ha obtenido el listado de usuarios");

		return DAO_USUARIO.obtenerTodos();
	}

	@Override
	public Optional<Usuario> obtenerUsuarioPorId(Long id) {
		log.info("Se ha pedido un usuario por id " + id);
		
		return DAO_USUARIO.obtenerPorId(id);
	}

	@Override
	public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
		log.info("Se ha pedido un usuario por email " + email);
		
		return DAO_USUARIO.obtenerPorEmail(email);
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		log.info("Se va a crear un usuario " + usuario);

		return DAO_USUARIO.insertar(usuario);
	}

	@Override
	public Usuario modificarUsuario(Usuario usuario) {
		log.info("Se va a modificar un usuario " + usuario);

		return DAO_USUARIO.modificar(usuario);
	}

	@Override
	public void borrarUsuario(Long id) {
		log.info("Se va a borrar un usuario " + id);

		DAO_USUARIO.borrar(id);
	}

}
