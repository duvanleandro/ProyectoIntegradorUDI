package Logica;

import Entidad.Solicitud;
import Entidad.Salas;
import Entidad.Equipos;

import javax.persistence.*;
import java.util.List;

public class GestionarSolicitud {

    private EntityManagerFactory emf;
    private EntityManager em;

    public GestionarSolicitud() {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }

    public boolean aceptarSolicitud(Long idSolicitud) {
        return cambiarEstadoSolicitud(idSolicitud, "ACEPTADO");
    }

    public boolean rechazarSolicitud(Long idSolicitud) {
        return cambiarEstadoSolicitud(idSolicitud, "RECHAZADO");
    }

    private boolean cambiarEstadoSolicitud(Long idSolicitud, String nuevoEstado) {
        boolean exito = false;

        try {
            em.getTransaction().begin();
            Solicitud solicitud = em.find(Solicitud.class, idSolicitud);

            if (solicitud != null && "PENDIENTE".equalsIgnoreCase(solicitud.getEstado())) {
                solicitud.setEstado(nuevoEstado);
                em.merge(solicitud);
                em.getTransaction().commit();
                exito = true;
            } else {
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        return exito;
    }

    public List<Solicitud> listarSolicitudesPendientes() {
        try {
            return em.createQuery("SELECT s FROM Solicitud s WHERE s.estado = :estado", Solicitud.class)
                    .setParameter("estado", "PENDIENTE")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // Nuevo método para listar todas las Salas
    public List<Salas> listarSalas() {
        try {
            return em.createQuery("SELECT s FROM Salas s", Salas.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // Nuevo método para listar todos los Equipos
    public List<Equipos> listarEquipos() {
        try {
            return em.createQuery("SELECT e FROM Equipos e", Equipos.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public void cerrar() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}
