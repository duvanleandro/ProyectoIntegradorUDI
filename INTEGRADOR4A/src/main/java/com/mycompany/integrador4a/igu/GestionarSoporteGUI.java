package com.mycompany.integrador4a.igu;

import Logica.GestionarSoporte;
import Entidad.Soporte;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GestionarSoporteGUI extends javax.swing.JFrame {

    private EntityManagerFactory emf;
    // Ya no usaremos 'em' como atributo global para evitar caché
    // private EntityManager em;

    // Modelo global para la tabla
    private DefaultTableModel modelo;

    public GestionarSoporteGUI() {
        initComponents();

        inicializarTabla();  // Inicializa el modelo y la tabla
        cargarDatosSoporte(); // Carga datos en el modelo sin cambiarlo
        configurarEventos();
    }

    // Inicializa el modelo y la configuración inicial de la tabla
    private void inicializarTabla() {
        String[] columnas = {"ID Soporte", "ID Usuario", "Nombre", "Fecha", "Tipo Solicitud", "Mensaje"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la columna "Mensaje" (index 5) es editable para el botón
                return column == 5;
            }
        };

        TablaSoporte.setModel(modelo);
        TablaSoporte.setRowHeight(30);

        configurarTablaBotones();
    }

    // Método para cargar datos en la tabla, actualizando filas sin cambiar el modelo
    public void cargarDatosSoporte() {
        modelo.setRowCount(0); // Limpia filas existentes
        EntityManager localEm = null;

        try {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("PU"); // Ajusta el nombre
            }

            localEm = emf.createEntityManager();

            List<Soporte> listaSoportes = localEm.createQuery(
                    "SELECT s FROM Soporte s JOIN FETCH s.usuario ORDER BY s.id", Soporte.class)
                    .getResultList();

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
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando datos: " + e.getMessage());
        } finally {
            if (localEm != null && localEm.isOpen()) {
                localEm.close();
            }
        }
    }

    // Configura la columna mensaje para que tenga botones "Ver"
    private void configurarTablaBotones() {
        // Columna 5 es "Mensaje"
        TablaSoporte.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        TablaSoporte.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    // Renderer para mostrar el botón "Ver"
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Ver");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor para el botón que abre ventana con mensaje
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String mensaje;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Ver");
            button.setOpaque(true);

            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            mensaje = (String) table.getModel().getValueAt(row, column);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                JOptionPane.showMessageDialog(GestionarSoporteGUI.this,
                        mensaje,
                        "Mensaje completo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            clicked = false;
            return "Ver";
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }

    private void configurarEventos() {
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = TablaSoporte.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Object valorId = TablaSoporte.getValueAt(filaSeleccionada, 0); // Columna ID Soporte

                    Long idSoporte = null;
                    if (valorId instanceof Long) {
                        idSoporte = (Long) valorId;
                    } else if (valorId instanceof Integer) {
                        idSoporte = ((Integer) valorId).longValue();
                    } else if (valorId instanceof String) {
                        idSoporte = Long.parseLong((String) valorId);
                    } else {
                        JOptionPane.showMessageDialog(GestionarSoporteGUI.this,
                                "El ID del soporte tiene un formato inesperado.");
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(
                            GestionarSoporteGUI.this,
                            "¿Estás seguro de eliminar este soporte?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Aquí hay que crear un EntityManager para eliminar y luego cerrar
                        EntityManager localEm = null;
                        try {
                            localEm = emf.createEntityManager();
                            GestionarSoporte.eliminarSoportePorId(idSoporte, localEm);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(GestionarSoporteGUI.this, "Error al eliminar soporte: " + ex.getMessage());
                        } finally {
                            if (localEm != null && localEm.isOpen()) {
                                localEm.close();
                            }
                        }

                        cargarDatosSoporte(); // Recargar la tabla sin cambiar modelo
                    }
                } else {
                    JOptionPane.showMessageDialog(GestionarSoporteGUI.this,
                            "Por favor, selecciona un soporte para eliminar.");
                }
            }
        });
    }

    // Getters para otros botones si los usas
    public JButton getBtnMenuPrincipal() {
        return btnMenuPrincipal;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGestionarSoporte = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSoporte = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondoGestionSoporte = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGestionarSoporte.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblGestionarSoporte.setText("GESTIONAR SOPORTE");
        jPanel1.add(lblGestionarSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, 50));

        TablaSoporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablaSoporte);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1000, -1));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 570, 170, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 570, 170, 40));

        lblFondoGestionSoporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoGestionSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JTable TablaSoporte;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoGestionSoporte;
    private javax.swing.JLabel lblGestionarSoporte;
    // End of variables declaration//GEN-END:variables

}
