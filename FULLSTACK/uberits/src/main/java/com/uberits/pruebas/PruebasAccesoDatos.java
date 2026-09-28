package com.uberits.pruebas;

import com.uberits.accesodatos.DaoUsuario;
import com.uberits.entidades.Usuario;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class PruebasAccesoDatos {
	public static void main(String[] args) {
		DaoUsuario daoUsuario = ContenedorInyeccionDependencias.obtenerObjeto("dao.usuario", DaoUsuario.class);
		
		daoUsuario.insertar(new Usuario(null, "Javier", "javier@email.net", "javier", null));
	}
}
