package Logica;

import Entidad.Encuesta;
import Entidad.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RealizarEncuesta {

    private EntityManagerFactory emf;

    public RealizarEncuesta() {
        // El nombre "miUnidadPersistencia" debe coincidir con el nombre en persistence.xml
        emf = Persistence.createEntityManagerFactory("PU");
    }

    public boolean guardarEncuesta(Usuario usuario, String rta1, String rta2, String rta3) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Encuesta encuesta = new Encuesta();
            encuesta.setUsuario(usuario);
            encuesta.setPregunta1(rta1);
            encuesta.setPregunta2(rta2);
            encuesta.setPregunta3(rta3);

            em.persist(encuesta);

            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
    