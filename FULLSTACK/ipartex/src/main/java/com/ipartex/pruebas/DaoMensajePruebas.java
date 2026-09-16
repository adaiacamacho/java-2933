package com.ipartex.pruebas;

import java.time.LocalDateTime;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.accesodatos.arraylist.DaoMensajeArrayListMejorada;
import com.ipartex.entidades.Mensaje;

public class DaoMensajePruebas {
	public static void main(String[] args) {
		DaoMensaje dao = new DaoMensajeArrayListMejorada();

		dao.insertar(new Mensaje(null, "Pedro", "Hola a todos", LocalDateTime.now()));
		dao.insertar(new Mensaje(null, "Juan", "Ya ha llegado el original", LocalDateTime.now()));
		
		System.out.println(dao.obtenerPorId(5L));
		System.out.println(dao.obtenerPorId(55L));
		
		dao.modificar(new Mensaje(5L, "Pedrito", "Hola a toditos", LocalDateTime.now()));
		dao.borrar(3L);
		
		for (Mensaje mensaje : dao.obtenerTodos()) {
			System.out.println(mensaje);
		}
		
//		Iterable<Mensaje> iterable = dao.obtenerTodos();
//		Iterator<Mensaje> iterator = iterable.iterator();
//		
//		while(iterator.hasNext()) {
//			Mensaje mensaje = iterator.next();
//			System.out.println(mensaje);
//		}
	}
}
