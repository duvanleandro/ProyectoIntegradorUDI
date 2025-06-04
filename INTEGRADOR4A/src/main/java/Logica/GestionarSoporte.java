package Logica;

import Entidad.Soporte;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GestionarSoporte {

    public static void cargarSoportesEnTabla(JTable tabla, EntityManager em) {
        String[] columnas = {"ID Soporte", "ID Usuario", "Nombre", "Fecha", "Tipo Solicitud", "Mensaje"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        try {
            // Consulta JPQL para traer soportes con usuario
            TypedQuery<Soporte> query = em.createQuery(
                "SELECT s FROM Soporte s JOIN FETCH s.usuario ORDER BY s.id", Soporte.class);
            List<Soporte> listaSoportes = query.getResultList();

            for (Soporte s : listaSoportes) {
                Object[] fila = new Object[6];
                fila[0] = s.getId();
                fila[1] = s.getUsuario().getId();
                fila[2] = s.getUsuario().getNombre();
                fila[3] = s.getFechaEnvio();  // LocalDate, muestra solo fecha, si quieres hora ajusta el tipo
                fila[4] = s.getTipoSoporte();
                fila[5] = s.getMensaje();

                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void eliminarSoportePorId(Long idSoporte, EntityManager em) {
    try {
        em.getTransaction().begin();

        Soporte soporte = em.find(Soporte.class, idSoporte);
        if (soporte != null) {
            em.remove(soporte);
        }

        em.getTransaction().commit();
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
    }
}

}
