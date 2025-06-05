package Logica;

import Entidad.Soporte;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GestionarSoporte {

    private BaseDeDatos bd;

    public GestionarSoporte() {
        bd = new BaseDeDatos();
    }

    public void cargarSoportesEnTabla(JTable tabla, EntityManager em) {
        String[] columnas = {"ID Soporte", "ID Usuario", "Nombre", "Fecha", "Tipo Solicitud", "Mensaje"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Soporte> listaSoportes = bd.obtenerListaSoportes(em);
        if (listaSoportes != null) {
            for (Soporte s : listaSoportes) {
                Object[] fila = new Object[6];
                fila[0] = s.getId();
                fila[1] = s.getUsuario().getId();
                fila[2] = s.getUsuario().getNombre();
                fila[3] = s.getFechaEnvio();
                fila[4] = s.getTipoSoporte();
                fila[5] = s.getMensaje();
                modelo.addRow(fila);
            }
        }
        tabla.setModel(modelo);
    }

    public boolean eliminarSoportePorId(Long idSoporte, EntityManager em) {
        return bd.eliminarSoporte(idSoporte, em);
    }
}