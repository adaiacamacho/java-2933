package com.uberits.pruebas;

import static com.uberits.config.ContenedorDependencias.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.uberits.entidades.Cliente;
import com.uberits.entidades.Pedido;
import com.uberits.entidades.Pedido.Linea;
import com.uberits.entidades.Plato;
import com.uberits.entidades.Restaurante;
import com.uberits.entidades.TipoComida;
import com.uberits.entidades.Usuario;

public class PruebasAccesoDatos {
	public static void main(String[] args) {
		// Rellenar datos de prueba
		try {
			// Tipo de comida
			TipoComida tc = new TipoComida();
			tc.setNombre("Italiana");
			tc = DAO_TIPO_COMIDA.insertar(tc);
			System.out.println("TipoComida creado: " + tc.getId() + " - " + tc.getNombre());

			// Restaurante
			Restaurante r = new Restaurante();
			r.setNombre("Trattoria Roma");
			List<TipoComida> tipos = new ArrayList<>();
			tipos.add(tc);
			r.setTiposComida(tipos);
			r = DAO_RESTAURANTE.insertar(r);
			System.out.println("Restaurante creado: " + r.getId() + " - " + r.getNombre());

			// Plato
			Plato p = new Plato();
			p.setNombre("Pizza Margarita");
			p.setDescripcion("Pizza clásica con tomate, mozzarella y albahaca");
			p.setPrecio(new BigDecimal("8.5"));
			p.setRestaurante(r);
			p = DAO_PLATO.insertar(p);
			System.out.println("Plato creado: " + p.getId() + " - " + p.getNombre());

			// Usuario y cliente
			Usuario u = new Usuario();
			u.setNombre("Juan Perez");
			u.setEmail("juan@example.com");
			u.setPassword("1234");
			u = DAO_USUARIO.insertar(u);
			System.out.println("Usuario creado: " + u.getId() + " - " + u.getEmail());

			com.uberits.entidades.Cliente c = new Cliente();
			c.setNif("12345678A");
			c.setNombre("Juan Perez");
			c.setTelefono("600000000");
			c.setDireccion("Calle Falsa 123");
			c.setUsuario(u);
			c = DAO_CLIENTE.insertar(c);
			System.out.println("Cliente creado: " + c.getId() + " - " + c.getNombre());

			// Asociar el cliente creado al usuario (es obligatorio que Usuario tenga
			// referencia a Cliente)
			u.setCliente(c);
			DAO_USUARIO.modificar(u);
			System.out.println("Usuario actualizado con cliente: " + u.getId() + " -> cliente " + c.getId());

			// Pedido
			Pedido pedido = new Pedido();
			pedido.setCliente(c);
			pedido = DAO_PEDIDO.insertar(pedido);
			System.out.println("Pedido creado: " + pedido.getId());

			// Línea de pedido
			Linea linea = new Linea();
			linea.setPedido(pedido);
			linea.setPlato(p);
			linea.setCantidad(2);
			linea = DAO_PEDIDO_LINEA.insertar(linea);
			System.out.println("Linea de pedido creada: " + linea.getId() + " (pedido=" + pedido.getId() + ", plato="
					+ p.getId() + ")");

			// --- Más datos para variedad ---
			TipoComida tc2 = new TipoComida();
			tc2.setNombre("Mexicana");
			tc2 = DAO_TIPO_COMIDA.insertar(tc2);
			System.out.println("TipoComida creado: " + tc2.getId() + " - " + tc2.getNombre());

			TipoComida tc3 = new TipoComida();
			tc3.setNombre("Japonesa");
			tc3 = DAO_TIPO_COMIDA.insertar(tc3);
			System.out.println("TipoComida creado: " + tc3.getId() + " - " + tc3.getNombre());

			// Restaurante mexicano
			Restaurante r2 = new Restaurante();
			r2.setNombre("El Sabor Mex");
			r2.setTiposComida(Arrays.asList(tc2));
			r2 = DAO_RESTAURANTE.insertar(r2);
			System.out.println("Restaurante creado: " + r2.getId() + " - " + r2.getNombre());

			// Platos adicionales
			Plato p2 = new Plato();
			p2.setNombre("Tacos al Pastor");
			p2.setDescripcion("Tacos con carne al pastor y piña");
			p2.setPrecio(new BigDecimal("7.50"));
			p2.setRestaurante(r2);
			p2 = DAO_PLATO.insertar(p2);
			System.out.println("Plato creado: " + p2.getId() + " - " + p2.getNombre());

			Plato p3 = new Plato();
			p3.setNombre("Ensalada César");
			p3.setDescripcion("Ensalada con pollo y salsa César");
			p3.setPrecio(new BigDecimal("6.00"));
			p3.setRestaurante(r);
			p3 = DAO_PLATO.insertar(p3);
			System.out.println("Plato creado: " + p3.getId() + " - " + p3.getNombre());

			// Más usuarios: algunos con cliente asociado y otros sin cliente
			Usuario u2 = new Usuario();
			u2.setNombre("Ana López");
			u2.setEmail("ana@example.com");
			u2.setPassword("abcd");
			u2 = DAO_USUARIO.insertar(u2);
			System.out.println("Usuario creado: " + u2.getId() + " - " + u2.getEmail());

			com.uberits.entidades.Cliente c2 = new Cliente();
			c2.setNif("87654321B");
			c2.setNombre("Ana López");
			c2.setTelefono("611111111");
			c2.setDireccion("Avenida Siempre Viva 1");
			c2.setUsuario(u2);
			c2 = DAO_CLIENTE.insertar(c2);
			System.out.println("Cliente creado: " + c2.getId() + " - " + c2.getNombre());

			// Asociar cliente al usuario correspondiente
			u2.setCliente(c2);
			DAO_USUARIO.modificar(u2);
			System.out.println("Usuario actualizado con cliente: " + u2.getId() + " -> cliente " + c2.getId());

			Usuario u3 = new Usuario();
			u3.setNombre("Carlos Ruiz");
			u3.setEmail("carlos@example.com");
			u3.setPassword("zzzz");
			u3 = DAO_USUARIO.insertar(u3);
			System.out.println("Usuario creado (sin cliente): " + u3.getId() + " - " + u3.getEmail());

			Usuario u4 = new Usuario();
			u4.setNombre("Invitado");
			u4.setEmail("guest@example.com");
			u4.setPassword("guest");
			u4 = DAO_USUARIO.insertar(u4);
			System.out.println("Usuario creado (sin cliente): " + u4.getId() + " - " + u4.getEmail());

			// Pedidos adicionales para clientes
			Pedido pedido2 = new Pedido();
			pedido2.setCliente(c2);
			pedido2 = DAO_PEDIDO.insertar(pedido2);
			System.out.println("Pedido creado: " + pedido2.getId() + " para cliente " + c2.getId());

			Linea linea2 = new Linea();
			linea2.setPedido(pedido2);
			linea2.setPlato(p2);
			linea2.setCantidad(1);
			linea2 = DAO_PEDIDO_LINEA.insertar(linea2);
			System.out.println("Linea de pedido creada: " + linea2.getId() + " (pedido=" + pedido2.getId() + ", plato="
					+ p2.getId() + ")");

			Pedido pedido3 = new Pedido();
			pedido3.setCliente(c);
			pedido3 = DAO_PEDIDO.insertar(pedido3);
			System.out.println("Pedido creado: " + pedido3.getId() + " para cliente " + c.getId());

			Linea linea3 = new Linea();
			linea3.setPedido(pedido3);
			linea3.setPlato(p3);
			linea3.setCantidad(3);
			linea3 = DAO_PEDIDO_LINEA.insertar(linea3);
			System.out.println("Linea de pedido creada: " + linea3.getId() + " (pedido=" + pedido3.getId() + ", plato="
					+ p3.getId() + ")");
			
			Usuario ana = DAO_USUARIO.buscarPorEmail("ana@example.com").get();
			
			System.out.println(ana);
			
			Cliente anaCliente = ana.getCliente();
			
			System.out.println(anaCliente);
			
//			Da LazyInicializationException
//			for(Pedido pedidoAna: anaCliente.getPedidos()) {
//				System.out.println(pedidoAna);
//			}
			
			for(Pedido unPedido: DAO_PEDIDO.obtenerTodos()) {
				System.out.println(unPedido);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
