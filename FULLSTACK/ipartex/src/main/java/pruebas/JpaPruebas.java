package pruebas;

import java.time.LocalDateTime;

import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaPruebas {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.ipartex.entidades");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(new Mensaje(null, "Javier", "Hola desde JPA", LocalDateTime.now()));
		em.persist(new Mensaje(null, "Pedro", "Qué tal", LocalDateTime.now()));
		em.persist(new Mensaje(null, "Juan", "Pues yo bien", LocalDateTime.now()));

		Iterable<Mensaje> mensajes = em.createQuery("from Mensaje", Mensaje.class).getResultList();

		for (Mensaje mensaje : mensajes) {
			System.out.println(mensaje);
		}
		
		em.persist(new Usuario(null, "user1", "us1@gmail.com", "1234"));
		em.persist(new Usuario(null, "user2", "us2@hotmail.com", "7890"));
		em.persist(new Usuario(null, "user3", "us3@yahoo.com", "5555"));
		
		Iterable<Usuario> usuarios= em.createQuery("from Usuario", Usuario.class).getResultList();
		
		for(Usuario usuario:usuarios) {
			System.out.println(usuario);
		}
	}
}
