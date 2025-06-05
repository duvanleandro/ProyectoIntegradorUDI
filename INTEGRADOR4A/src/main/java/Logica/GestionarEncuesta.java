package Logica;

import Entidad.Encuesta;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GestionarEncuesta {

    private EntityManagerFactory emf;

    public GestionarEncuesta() {
        // Usa el nombre de tu unidad de persistencia desde persistence.xml
        emf = Persistence.createEntityManagerFactory("PU");
    }

    public List<Encuesta> obtenerTodasLasEncuestas() {
        EntityManager em = emf.createEntityManager();
        List<Encuesta> lista = em.createQuery("SELECT e FROM Encuesta e", Encuesta.class).getResultList();
        em.close();
        return lista;
    }
}
