package Logica;

import Entidad.Sanciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GestionarSanciones {

    private EntityManagerFactory emf;
    private EntityManager em;

    public GestionarSanciones() {
        emf = Persistence.createEntityManagerFactory("PU"); // Cambia por tu PU name
        em = emf.createEntityManager();
    }

    public List<Sanciones> listarSanciones() {
        return em.createQuery("SELECT s FROM Sanciones s", Sanciones.class).getResultList();
    }

    // Cierra recursos si lo deseas
    public void cerrar() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
    
    public boolean eliminarSancionPorId(Long idSancion) {
    try {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Sanciones sancion = em.find(Sanciones.class, idSancion);
        if (sancion != null) {
            em.remove(sancion);
            em.getTransaction().commit();
            em.close();
            return true;
        }

        em.getTransaction().rollback();
        em.close();
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public String obtenerTextoSancionesPorUsuario(Long idUsuario) {
    List<Sanciones> sanciones = em.createQuery(
        "SELECT s FROM Sanciones s WHERE s.usuario.id = :idUsuario", Sanciones.class)
        .setParameter("idUsuario", idUsuario)
        .getResultList();

    if (sanciones.isEmpty()) {
        return "No tiene sanciones registradas.";
    }

    StringBuilder sb = new StringBuilder();
    for (Sanciones sancion : sanciones) {
        sb.append("Sanción #").append(sancion.getId()).append("\n")
          .append("Tipo de Sanción: ").append(sancion.getTipoSancion()).append("\n")
          .append("Motivo: ").append(sancion.getMensaje()).append("\n\n");
    }
    return sb.toString();
}
public boolean tieneSanciones(Long idUsuario) {
    EntityManager emLocal = emf.createEntityManager();
    try {
        Long count = emLocal.createQuery(
            "SELECT COUNT(s) FROM Sanciones s WHERE s.usuario.id = :idUsuario", Long.class)
            .setParameter("idUsuario", idUsuario)
            .getSingleResult();
        return count != null && count > 0;
    } finally {
        emLocal.close();
    }
}


}
