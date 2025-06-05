package Logica;

import Entidad.Solicitud;

import javax.persistence.*;

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

    public void cerrar() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}
