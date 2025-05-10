// TablaSolicitudesAdmin.java
package Interfaz;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class TablaSolicitudesAdmin {

    public static class RenderBoton implements TableCellRenderer {
        private final String texto;
        public RenderBoton(String texto) { this.texto = texto; }
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean seleccionado, boolean foco,
                                                       int fila, int columna) {
            JButton boton = new JButton(texto);
            return boton;
        }
    }

    public static class EditorBoton extends AbstractCellEditor
                                  implements TableCellEditor, ActionListener {
        private final JButton boton;
        private JTable tabla;
        private int fila;
        private final String texto;
        public EditorBoton(String texto) {
            this.texto = texto;
            boton = new JButton(texto);
            boton.addActionListener(this);
        }
        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor,
                                                     boolean seleccionado, int fila, int columna) {
            this.tabla = tabla;
            this.fila = fila;
            return boton;
        }
        @Override
        public Object getCellEditorValue() { return texto; }
        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            JFrame v = new JFrame("Elementos solicitados");
            v.setSize(500,250);
            String[] cols = {"Tipo de servicio","Elemento reservado"};
            String data = (String) tabla.getValueAt(fila,7);
            String[] pares = data.split(";");
            Object[][] dat = new Object[pares.length][2];
            for(int i=0;i<pares.length;i++){
                String[] p = pares[i].split(":",2);
                dat[i][0]=p[0];
                dat[i][1]=p[1];
            }
            JTable t = new JTable(dat,cols);
            v.add(new JScrollPane(t));
            v.setVisible(true);
        }
    }

    public static class RenderBotonAceptar implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean seleccionado, boolean foco,
                                                       int fila, int columna) {
            return new JButton("✔️");
        }
    }

    public static class EditorBotonAceptar extends AbstractCellEditor
                                         implements TableCellEditor, ActionListener {
        private final JButton boton = new JButton("✔️");
        public EditorBotonAceptar() { boton.addActionListener(this); }
        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor,
                                                     boolean seleccionado, int fila, int columna) {
            return boton;
        }
        @Override
        public Object getCellEditorValue() { return "✔️"; }
        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            JOptionPane.showMessageDialog(boton,"Solicitud aceptada.");
        }
    }

    public static class RenderBotonRechazar implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean seleccionado, boolean foco,
                                                       int fila, int columna) {
            return new JButton("❌");
        }
    }

    public static class EditorBotonRechazar extends AbstractCellEditor
                                        implements TableCellEditor, ActionListener {
        private final JButton boton = new JButton("❌");
        private JTable tabla;
        private int fila;
        public EditorBotonRechazar() { boton.addActionListener(this); }
        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor,
                                                     boolean seleccionado, int fila, int columna) {
            this.tabla = tabla;
            this.fila = fila;
            return boton;
        }
        @Override
        public Object getCellEditorValue() { return "❌"; }
        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            JTextArea area = new JTextArea(5,30);
            int r = JOptionPane.showConfirmDialog(boton,new JScrollPane(area),
                        "Comentario de rechazo",JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
            if(r==JOptionPane.OK_OPTION){
                String c = area.getText().trim();
                JOptionPane.showMessageDialog(boton,
                    "Solicitud rechazada con éxito.\nComentario:\n"+c);
            }
        }
    }

    public static class RenderBotonVerUsuario implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean seleccionado, boolean foco,
                                                       int fila, int columna) {
            return new JButton("Ver");
        }
    }

    public static class EditorBotonVerUsuario extends AbstractCellEditor
                                         implements TableCellEditor, ActionListener {
        private final JButton boton = new JButton("Ver");
        private JTable tabla;
        private int fila;
        private String info;
        public EditorBotonVerUsuario() { boton.addActionListener(this); }
        @Override
        public Component getTableCellEditorComponent(JTable tabla, Object valor,
                                                     boolean seleccionado, int fila, int columna) {
            this.tabla = tabla;
            this.fila = fila;
            info = (String) tabla.getValueAt(fila,3);
            return boton;
        }
        @Override
        public Object getCellEditorValue() { return info; }
        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            String[] p = info.split(":",3);
            String n = p.length>0?p[0]:"";
            String a = p.length>1?p[1]:"";
            String m = p.length>2?p[2]:"Desconocido";
            JTextArea area = new JTextArea(
                "Nombre: "+n+"\n"+
                "Apellido: "+a+"\n"+
                "Correo: "+m
            );
            area.setEditable(false);
            JOptionPane.showMessageDialog(boton,new JScrollPane(area),
                "Información del usuario",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
