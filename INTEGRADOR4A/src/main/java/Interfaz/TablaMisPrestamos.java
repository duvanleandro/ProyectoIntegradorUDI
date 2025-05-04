// Interfaz/TablaMisPrestamos.java
package Interfaz;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class TablaMisPrestamos {

    public static class RenderizadorBoton extends JButton implements TableCellRenderer {
        public RenderizadorBoton(String etiqueta) { setText(etiqueta); }
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
        private int fila;
        private JTable tabla;

        public EditorBoton(JCheckBox casilla, String etiqueta) {
            super(casilla);
            this.etiqueta = etiqueta;
            boton = new JButton(etiqueta);
            boton.addActionListener(e -> {
                fireEditingStopped();
                if ("Ver Detalles".equals(etiqueta)) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    JButton btnElem = new JButton("Ver Elementos Solicitados");
                    JButton btnCom = new JButton("Ver Comentarios del Administrador");
                    btnElem.addActionListener(ev -> {
                        String[] cols = {"Tipo servicio","Elemento reservado"};
                        Object[][] dat = {
                            {"Sala de informática","Sala A - Diseño Gráfico"},
                            {"Equipo audiovisual","Proyector"},
                            {"Equipo audiovisual","Micrófono"}
                        };
                        JTable t = new JTable(dat,cols);
                        JScrollPane sc = new JScrollPane(t);
                        sc.setPreferredSize(new Dimension(400,100));
                        JOptionPane.showMessageDialog(boton, sc,
                            "Elementos Solicitados", JOptionPane.INFORMATION_MESSAGE);
                    });
                    btnCom.addActionListener(ev -> {
                        JOptionPane.showMessageDialog(boton,
                            "Comentario del administrador: Aprobado con observaciones.",
                            "Comentario", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panel.add(btnElem);
                    panel.add(Box.createVerticalStrut(10));
                    panel.add(btnCom);
                    JOptionPane.showOptionDialog(boton, panel, "Ver Detalles",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, new Object[]{}, null);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor,
                                                     boolean sel, int fila, int col) {
            this.tabla = tabla; this.fila = fila;
            boton.setText(etiqueta);
            return boton;
        }

        @Override
        public Object getCellEditorValue() { return etiqueta; }
    }

    public static class RenderizadorCancelar extends JButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean sel, boolean foco,
                                                       int fila, int col) {
            setText("❌");
            String est = (String) tabla.getValueAt(fila,3);
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
                ((DefaultTableModel)tabla.getModel()).removeRow(fila);
            });
        }
        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor,
                                                     boolean sel, int fila, int col) {
            this.tabla = tabla; this.fila = fila;
            String est = (String) tabla.getValueAt(fila,3);
            boton.setEnabled("Pendiente".equalsIgnoreCase(est));
            return boton;
        }
        @Override
        public Object getCellEditorValue() { return "❌"; }
    }
}

