package Logica;

import Entidad.Sanciones;
import Entidad.Usuario;
import com.mycompany.integrador4a.igu.RealizarSancion.SancionDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RealizarSancionAdmin {

    private EntityManagerFactory emf;

    public RealizarSancionAdmin() {
        emf = Persistence.createEntityManagerFactory("PU"); // Ajusta el nombre
    }

    public boolean guardarSancion(SancionDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Buscar al usuario a sancionar por ID
            Usuario usuario = em.find(Usuario.class, dto.idUsuario);
            if (usuario == null) {
                System.err.println("No se encontró el usuario con ID: " + dto.idUsuario);
                return false;
            }

            // Crear la sanción
            Sanciones sancion = new Sanciones();
            sancion.setUsuario(usuario);
            sancion.setTipoSancion(dto.tipoSancion);
            sancion.setMensaje(dto.motivo);

            // Guardar
            em.persist(sancion);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
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
