package com.ipartex.pruebas;

import java.util.Iterator;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.accesodatos.arraylist.DaoMensajeArrayList;
import com.ipartex.entidades.Mensaje;

public class DaoMensajePruebas {
	public static void main(String[] args) {
		DaoMensaje dao = new DaoMensajeArrayList();

		for (Mensaje mensaje : dao.obtenerTodos()) {
			System.out.println(mensaje);
		}
		
		Iterable<Mensaje> iterable = dao.obtenerTodos();
		Iterator<Mensaje> iterator = iterable.iterator();
		
		while(iterator.hasNext()) {
			Mensaje mensaje = iterator.next();
			System.out.println(mensaje);
		}
	}
}
