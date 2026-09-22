package com.ipartex.accesodatos.sqlite;

import java.util.Optional;

import com.ipartex.accesodatos.DaoUsuario;
import com.ipartex.entidades.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoUsuarioSqlite implements DaoUsuario {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.ipartex.entidades");
	EntityManager em = emf.createEntityManager();
	EntityTransaction t = em.getTransaction();

	
	
	@Override
	public Iterable<Usuario> obtenerTodos() {
		t.begin();
		Iterable<Usuario> usuarios= em.createQuery("from Usuario", Usuario.class).getResultList();
		return usuarios;
	}

	@Override
	public Iterable<Usuario> obtenerParaPantalla() {
		t.begin();
		Iterable<Usuario> usuarios= em.createQuery("from Usuario", Usuario.class).getResultList();
		return usuarios;
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		t.begin();		
			
		return Optional.of(em.createQuery("from Usuario where id="+id, Usuario.class).getSingleResult());
	}

	@Override
	public Usuario insertar(Usuario user) {
		t.begin();

		return null;
	}

	@Override
	public Usuario modificar(Usuario user) {
		t.begin();

		return null;
	}

	@Override
	public void borrar(Long id) {
		t.begin();
		
	}

}
