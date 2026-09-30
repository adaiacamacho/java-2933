package com.uberits.config;

import com.uberits.accesodatos.DaoCliente;
import com.uberits.accesodatos.DaoPedido;
import com.uberits.accesodatos.DaoPedidoLinea;
import com.uberits.accesodatos.DaoPlato;
import com.uberits.accesodatos.DaoRestaurante;
import com.uberits.accesodatos.DaoTipoComida;
import com.uberits.accesodatos.DaoUsuario;
import com.uberits.logicanegocio.AnonimoNegocio;
import com.uberits.logicanegocio.impl.AnonimoNegocioImpl;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class ContenedorDependencias {
	public static DaoUsuario DAO_USUARIO = ContenedorInyeccionDependencias.obtenerObjeto("dao.usuario",
			DaoUsuario.class);
	public static DaoCliente DAO_CLIENTE = ContenedorInyeccionDependencias.obtenerObjeto("dao.cliente",
			DaoCliente.class);
	public static DaoRestaurante DAO_RESTAURANTE = ContenedorInyeccionDependencias.obtenerObjeto("dao.restaurante",
			DaoRestaurante.class);
	public static DaoPlato DAO_PLATO = ContenedorInyeccionDependencias.obtenerObjeto("dao.plato", DaoPlato.class);
	public static DaoPedido DAO_PEDIDO = ContenedorInyeccionDependencias.obtenerObjeto("dao.pedido", DaoPedido.class);
	public static DaoTipoComida DAO_TIPO_COMIDA = ContenedorInyeccionDependencias.obtenerObjeto("dao.tipocomida",
			DaoTipoComida.class);
	public static DaoPedidoLinea DAO_PEDIDO_LINEA = ContenedorInyeccionDependencias.obtenerObjeto("dao.pedidolinea",
			DaoPedidoLinea.class);
	
	public static AnonimoNegocio ANONIMO_NEGOCIO = new AnonimoNegocioImpl(DAO_USUARIO);
}
