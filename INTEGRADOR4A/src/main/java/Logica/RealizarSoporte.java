package Logica;

import Entidad.Soporte;
import Entidad.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class RealizarSoporte {

    private EntityManagerFactory emf;

    public RealizarSoporte() {
        // Cambia "PU" por el nombre correcto en tu persistence.xml
        emf = Persistence.createEntityManagerFactory("PU");
    }

    public boolean guardarSoporte(Long idUsuario, String tipoSoporte, String mensaje) {
        EntityManager em = emf.createEntityManager();
        boolean exito = false;

        try {
            em.getTransaction().begin();

            Usuario usuario = em.find(Usuario.class, idUsuario);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario con id " + idUsuario + " no encontrado");
            }

            Soporte soporte = new Soporte();
            soporte.setMensaje(mensaje);
            soporte.setFechaEnvio(LocalDate.now());
            soporte.setUsuario(usuario);
            soporte.setTipoSoporte(tipoSoporte);

            em.persist(soporte);
            em.getTransaction().commit();

            exito = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            exito = false;
        } finally {
            em.close();
        }
        return exito;
    }

    public void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
