package com.mycompany.integrador4a.igu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import Entidad.Solicitud;
import Entidad.DetalleSolicitud;
import Entidad.Usuario;
import Logica.RealizarSolicitudP;

public class GestionarSolicitudes extends javax.swing.JFrame {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public GestionarSolicitudes(Usuario usuario) {
        initComponents();
        emf = Persistence.createEntityManagerFactory("PU");
        entityManager = emf.createEntityManager();

        cargarTablaSolicitudes();

        TablaSolicitudes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarEstadoBotones();
            }
        });

        btnAceptar.addActionListener(e -> {
            int fila = TablaSolicitudes.getSelectedRow();
            if (fila != -1) {
                Long idSolicitud = (Long) TablaSolicitudes.getModel().getValueAt(fila, 0);

                RealizarSolicitudP logica = new RealizarSolicitudP();
                boolean exito = logica.aceptarSolicitud(idSolicitud);
                // No cerrar el emf y em en RealizarSolicitudP, porque lo usarás después.
                if (exito) {
                    refrescarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo aceptar la solicitud.");
                }
            }
        });

        btnRechazar.addActionListener(e -> {
            int fila = TablaSolicitudes.getSelectedRow();
            if (fila != -1) {
                Long idSolicitud = (Long) TablaSolicitudes.getModel().getValueAt(fila, 0);

                RealizarSolicitudP logica = new RealizarSolicitudP();
                boolean exito = logica.rechazarSolicitud(idSolicitud);
                if (exito) {
                    refrescarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo rechazar la solicitud.");
                }
            }
        });
    }

    private void refrescarTabla() {
        if (entityManager.isOpen()) {
            entityManager.clear();
        }
        cargarTablaSolicitudes();
        actualizarEstadoBotones();
    }

    private void actualizarEstadoBotones() {
        int fila = TablaSolicitudes.getSelectedRow();
        if (fila == -1) {
            btnAceptar.setEnabled(false);
            btnRechazar.setEnabled(false);
            return;
        }

        String estado = TablaSolicitudes.getModel().getValueAt(fila, 6).toString();

        boolean pendiente = "PENDIENTE".equalsIgnoreCase(estado);
        btnAceptar.setEnabled(pendiente);
        btnRechazar.setEnabled(pendiente);
    }

    public void cargarTablaSolicitudes() {
        String[] columnas = {
            "ID Solicitud", "Usuario", "Fecha Solicitud", "Fecha Uso",
            "Hora Inicio", "Hora Fin", "Estado",
            "Elementos"  // Columna con botón "VER"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - EEEE", new Locale("es", "ES"));

        entityManager.clear();
        entityManager.getEntityManagerFactory().getCache().evictAll();

        String jpql = "SELECT DISTINCT s FROM Solicitud s LEFT JOIN FETCH s.detalles LEFT JOIN FETCH s.usuario";
        TypedQuery<Solicitud> query = entityManager.createQuery(jpql, Solicitud.class);
        List<Solicitud> listaSolicitudes = query.getResultList();

        for (Solicitud s : listaSolicitudes) {
            Object[] fila = new Object[8];
            fila[0] = s.getIdSolicitud();
            fila[1] = s.getUsuario().getNombre();
            fila[2] = formato.format(s.getFechaSolicitud());
            fila[3] = formato.format(s.getFechaUso());
            fila[4] = s.getHoraInicio();
            fila[5] = s.getHoraFin();
            fila[6] = s.getEstado();
            fila[7] = "VER";

            modelo.addRow(fila);
        }

        TablaSolicitudes.setModel(modelo);

        // Columna con botón VER
        TableColumn col = TablaSolicitudes.getColumn("Elementos");
        col.setCellRenderer(new ButtonRenderer());
        col.setCellEditor(new ButtonEditor(new JCheckBox(), listaSolicitudes));
    }

    // Renderer para el botón VER
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Editor para el botón VER
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private boolean clicked;
        private int fila;
        private JTable table;
        private List<Solicitud> solicitudes;

        public ButtonEditor(JCheckBox checkBox, List<Solicitud> solicitudes) {
            super(checkBox);
            this.solicitudes = solicitudes;
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(e -> {
                fireEditingStopped();
                if (fila >= 0 && fila < solicitudes.size()) {
                    Solicitud solicitud = solicitudes.get(fila);
                    mostrarDetallesSolicitud(solicitud);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.table = table;
            this.fila = row;
            button.setText((value == null) ? "" : value.toString());
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            clicked = false;
            return button.getText();
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    private void mostrarDetallesSolicitud(Solicitud solicitud) {
        JDialog dialog = new JDialog(this, "Detalles de la Solicitud #" + solicitud.getIdSolicitud(), true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        String[] columnas = {"Tipo Servicio", "Elemento"};
        DefaultTableModel modelDetalles = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (DetalleSolicitud d : solicitud.getDetalles()) {
            modelDetalles.addRow(new Object[]{d.getTipoServicio(), d.getElemento()});
        }

        JTable tablaDetalles = new JTable(modelDetalles);
        JScrollPane scroll = new JScrollPane(tablaDetalles);

        dialog.add(scroll);
        dialog.setVisible(true);
    }

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
        lblGestionSolicitud = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSolicitudes = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnRechazar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondoGestionSolicitud = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGestionSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblGestionSolicitud.setText("GESTION DE SOLICITUDES");
        jPanel1.add(lblGestionSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 640, 50));

        TablaSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaSolicitudes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 231, 990, 320));

        btnAceptar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAceptar.setText("ACEPTAR");
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 170, 40));

        btnRechazar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRechazar.setText("RECHAZAR");
        jPanel1.add(btnRechazar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, 170, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 570, 170, 40));

        lblFondoGestionSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoGestionSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JTable TablaSolicitudes;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnRechazar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoGestionSolicitud;
    private javax.swing.JLabel lblGestionSolicitud;
    // End of variables declaration//GEN-END:variables

}
