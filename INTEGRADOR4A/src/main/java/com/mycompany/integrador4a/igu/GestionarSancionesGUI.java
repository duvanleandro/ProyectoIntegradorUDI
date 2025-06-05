package com.mycompany.integrador4a.igu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

import Logica.GestionarSanciones;
import Entidad.Sanciones;
import java.util.List;

public class GestionarSancionesGUI extends javax.swing.JFrame {

    private GestionarSanciones gestionarLogicaGUI;

    public GestionarSancionesGUI() {
        initComponents();
        configurarTablaSanciones();

        gestionarLogicaGUI = new GestionarSanciones();
        cargarDatosDeBase();

        // Asociar la acción al botón eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSancionSeleccionada();
            }
        });
    }


    public void cargarDatosDeBase() {
        DefaultTableModel modelo = (DefaultTableModel) TablaSanciones.getModel();
        modelo.setRowCount(0); // limpia tabla

        List<Sanciones> lista = gestionarLogicaGUI.listarSanciones();
        for (Sanciones sancion : lista) {
            Long idUsuario = sancion.getUsuario().getId();
            modelo.addRow(new Object[]{
                sancion.getId(),
                idUsuario,
                sancion.getTipoSancion(),
                sancion.getMensaje()
            });
        }
    }

    public JButton getBtnMenuPrincipal() {
        return btnMenuPrincipal;
    }

    public JButton getBtnSancionar() {
        return btnSancionar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    private void configurarTablaSanciones() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la columna MENSAJE (índice 3) es editable (para el botón)
                return column == 3;
            }
        };

        modelo.addColumn("ID_SANCION");
        modelo.addColumn("ID_USUARIO");
        modelo.addColumn("TIPO_SANCION");
        modelo.addColumn("MENSAJE");

        TablaSanciones.setModel(modelo);

        // Agregar datos de prueba (opcional)
        modelo.addRow(new Object[]{1, 101, "Suspensión", "El estudiante fue suspendido por 3 días."});
        modelo.addRow(new Object[]{2, 102, "Advertencia", "Advertencia por conducta inapropiada en clase."});

        // Aplicar botón en la columna MENSAJE
        TablaSanciones.getColumn("MENSAJE").setCellRenderer(new BotonRendererEditor(TablaSanciones));
        TablaSanciones.getColumn("MENSAJE").setCellEditor(new BotonRendererEditor(TablaSanciones));
    }

    // Clase interna para el botón "VER" en la tabla
    private class BotonRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        private final JButton button;
        private String mensajeActual;

        public BotonRendererEditor(JTable table) {
            button = new JButton("VER");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(table, mensajeActual, "Mensaje de Sanción", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            return button;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                     int row, int column) {
            mensajeActual = (value != null) ? value.toString() : "";
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "VER";
        }
    }

    private void eliminarSancionSeleccionada() {
        int filaSeleccionada = TablaSanciones.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una sanción para eliminar.", "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la sanción seleccionada?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Object valor = TablaSanciones.getValueAt(filaSeleccionada, 0);
        Long idSancion = null;
        if (valor instanceof Long) {
            idSancion = (Long) valor;
        } else if (valor instanceof Integer) {
            idSancion = ((Integer) valor).longValue();
        } else if (valor instanceof String) {
            idSancion = Long.parseLong((String) valor);
        }

        boolean eliminado = gestionarLogicaGUI.eliminarSancionPorId(idSancion);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Sanción eliminada correctamente.");
            cargarDatosDeBase();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la sanción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGestionSanciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSanciones = new javax.swing.JTable();
        btnSancionar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondoGestionSanciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGestionSanciones.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblGestionSanciones.setText("GESTION DE SANCIONES");
        jPanel1.add(lblGestionSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 570, 70));

        TablaSanciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablaSanciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1020, -1));

        btnSancionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSancionar.setText("AGREGAR");
        jPanel1.add(btnSancionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 590, 170, 40));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 590, 170, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 590, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 590, 170, 40));

        lblFondoGestionSanciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoGestionSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaSanciones;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSancionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoGestionSanciones;
    private javax.swing.JLabel lblGestionSanciones;
    // End of variables declaration//GEN-END:variables

}
