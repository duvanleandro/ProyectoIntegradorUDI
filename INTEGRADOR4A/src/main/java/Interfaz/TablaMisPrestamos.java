package Interfaz;

import Conexion.ConexionOracle;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TablaMisPrestamos {

    public static class RenderizadorBoton extends JButton implements TableCellRenderer {
        public RenderizadorBoton(String etiqueta) {
            setText(etiqueta);
        }

        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean sel, boolean foco,
                                                       int fila, int col) {
            setText(valor != null ? valor.toString() : "");
            return this;
        }
    }

    public static class EditorBoton extends DefaultCellEditor {
        private final JButton boton;
        private final String etiqueta;
        private final JFrame parent;
        private int fila;
        private JTable tabla;

        public EditorBoton(JCheckBox casilla, String etiqueta, JFrame parent) {
            super(casilla);
            this.etiqueta = etiqueta;
            this.parent = parent;
            boton = new JButton(etiqueta);
            boton.addActionListener(e -> {
                fireEditingStopped();
                if ("Ver Detalles".equals(etiqueta)) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JButton btnElem = new JButton("Ver Elementos Solicitados");
                    JButton btnCom  = new JButton("Ver Comentarios del Administrador");

                    btnElem.addActionListener(ev -> {
                        int modelRow    = tabla.convertRowIndexToModel(fila);
                        int idSolicitud = (int) tabla.getModel().getValueAt(modelRow, 0);

                        ConexionOracle co = new ConexionOracle();
                        try (Connection cn = co.conectar()) {
                            String sql = "SELECT TIPO_SERVICIO, ELEMENTO " +
                                         "FROM DETALLE_SOLICITUD " +
                                         "WHERE ID_SOLICITUD = ?";
                            PreparedStatement ps = cn.prepareStatement(sql);
                            ps.setInt(1, idSolicitud);
                            ResultSet rs = ps.executeQuery();
                            List<Object[]> datos = new ArrayList<>();
                            while (rs.next()) {
                                datos.add(new Object[]{
                                    rs.getString("TIPO_SERVICIO"),
                                    rs.getString("ELEMENTO")
                                });
                            }
                            String[] cols = {"Tipo servicio", "Elemento reservado"};
                            Object[][] dat = datos.toArray(new Object[0][]);
                            JTable t = new JTable(dat, cols);
                            JScrollPane sc = new JScrollPane(t);
                            sc.setPreferredSize(new Dimension(400, 100));
                            JOptionPane.showMessageDialog(parent, sc,
                                "Elementos Solicitados", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(parent,
                                "Error al cargar detalles: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                        } finally {
                            co.cerrarConexion();
                        }
                    });

                    btnCom.addActionListener(ev -> {
                        JOptionPane.showMessageDialog(parent,
                            "Comentario del administrador: Aprobado con observaciones.",
                            "Comentario", JOptionPane.INFORMATION_MESSAGE);
                    });

                    panel.add(btnElem);
                    panel.add(Box.createVerticalStrut(10));
                    panel.add(btnCom);

                    JOptionPane.showOptionDialog(parent, panel, "Ver Detalles",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, new Object[]{}, null);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor,
                                                     boolean sel, int fila, int col) {
            this.tabla = tabla;
            this.fila = fila;
            boton.setText(etiqueta);
            return boton;
        }

        @Override
        public Object getCellEditorValue() {
            return etiqueta;
        }
    }

    public static class RenderizadorCancelar extends JButton implements TableCellRenderer {
        public RenderizadorCancelar() {
            setText("❌");
        }

        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean sel, boolean foco,
                                                       int fila, int col) {
            // Estado está en la columna 3 de la vista: Fecha(0), Hora inicio(1), Hora final(2), Estado(3)
            String est = (String) tabla.getValueAt(fila, 3);
            setEnabled("Pendiente".equalsIgnoreCase(est));
            return this;
        }
    }

    public static class EditorCancelar extends DefaultCellEditor {
        private final JButton boton = new JButton("❌");
        private int fila;
        private JTable tabla;

        public EditorCancelar(JCheckBox casilla) {
            super(casilla);
            boton.addActionListener(e -> {
                fireEditingStopped();
        
                // Obtener el ID de la solicitud
                int modelRow = tabla.convertRowIndexToModel(fila);
                int idSolicitud = (int) tabla.getModel().getValueAt(modelRow, 0);
        
                // Confirmar acción de cancelación
                int opcion = JOptionPane.showConfirmDialog(tabla, 
                    "¿Estás seguro que deseas cancelar esta solicitud?", "Confirmar cancelación", 
                    JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    ConexionOracle co = new ConexionOracle();
                    try (Connection cn = co.conectar()) {
                        // SQL para actualizar el estado de la solicitud a 'Cancelado'
                        String sql = "UPDATE SOLICITUDES SET ESTADO = 'Cancelado' WHERE ID_SOLICITUD = ?";
                        PreparedStatement ps = cn.prepareStatement(sql);
                        ps.setInt(1, idSolicitud);
                        ps.executeUpdate();
                
                        // Actualizar la tabla para reflejar el cambio de estado
                        tabla.setValueAt("Cancelado", fila, 3);
                
                        // Deshabilitar el botón de la X (columna 5) en la fila correspondiente
                        JButton botonX = (JButton) tabla.getCellEditor(fila, 5).getTableCellEditorComponent(tabla, "❌", false, fila, 5);
                        botonX.setEnabled(false);  // Deshabilitar el botón de cancelar
                
                        // Forzar una actualización de la tabla
                        ((DefaultTableModel) tabla.getModel()).fireTableCellUpdated(fila, 4); // Para que la celda de "Estado" se actualice
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(tabla, 
                            "Error al cancelar la solicitud: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        co.cerrarConexion();
                    }
                    tabla.setValueAt("Cancelado", fila, 3); // Columna "Estado"
                    tabla.setValueAt(null, fila, 6);
                    
                    ((AbstractTableModel) tabla.getModel()).fireTableRowsUpdated(fila, fila);
                    tabla.repaint();

                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor, boolean sel,
                                                     int fila, int col) {
            this.tabla = tabla;
            this.fila = fila;
            return boton;
        }

        @Override
        public Object getCellEditorValue() {
            return "❌";
        }
    }
}
