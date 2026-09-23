package pruebas;

import java.time.LocalDateTime;

import com.ipartex.entidades.Mensaje;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaPruebas {

	public static void main(String[] args) {
		EntityTransaction t = null;
		
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.ipartex.entidades");
				EntityManager em = emf.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			em.persist(new Mensaje(null, "Javier", "Hola desde JPA", LocalDateTime.now()));
			em.persist(new Mensaje(null, "Pedro", "Qué tal", LocalDateTime.now()));
			em.persist(new Mensaje(null, "Juan", "Pues yo bien", LocalDateTime.now()));

			Iterable<Mensaje> mensajes = em.createQuery("from Mensaje", Mensaje.class).getResultList();

			for (Mensaje mensaje : mensajes) {
				System.out.println(mensaje);
			}

			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
		}
	}
}
