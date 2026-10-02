package com.uberits.pruebas;

import static com.uberits.config.ContenedorDependencias.*;

import java.util.List;
import java.util.Map.Entry;

import com.uberits.entidades.Usuario;

import bibliotecas.validaciones.ValidadorException;

public class PruebasValidador {
	public static void main(String[] args) {
		Usuario usuario = new Usuario();

		usuario.setNombre("   ");
		usuario.setEmail("abalñsdkhgñlasdj flñj asñldgh ñlkasjd fñlha sdñlgh añlsfj ñlasdgh ñlasdh flñahsd lñgh asñldfj alñsdkgh ñlasdjf lñashd glñkajsd glñjasdlñgh alñdfjlñasdjglñahsdgh");
		usuario.setPassword("ñlasjkd flñah sdlñgj asdflñasdh glñajsdflñkha sdlñgkj asñdlkfj ñalsdkgh ñlasdjf lñasdh glñjasd ja sdlñgh alñfj ñlaskdh gñlaskhd flñajsd glñh asdñaslkdñglasd");

		try {
			System.out.println("Validando " + usuario);
			
			VALIDADOR.validar(usuario, Usuario.class);
			
			System.out.println("TODO CORRECTO");
		} catch (ValidadorException e) {
			for (Entry<String, List<String>> error : e.getErrores().entrySet()) {
				System.out.printf("%s %s\n", error.getKey(), error.getValue());
			}
		}
	}
}
