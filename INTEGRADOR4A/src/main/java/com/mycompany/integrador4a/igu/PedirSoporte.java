package com.mycompany.integrador4a.igu;

import Entidad.Usuario;
import Logica.RealizarSoporte;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PedirSoporte extends javax.swing.JFrame {

    private Usuario usuarioActivo;  // Campo para guardar el usuario logueado
    private GestionarSoporteGUI gestionarSoporteGUI;

    public PedirSoporte(Usuario usuario, GestionarSoporteGUI GSoporte) {
        initComponents();

        this.usuarioActivo = usuario;  // Guardamos el usuario que llega
        this.gestionarSoporteGUI = GSoporte;  // Guardamos la referencia a la ventana de gestión

        // Agrupar los botones
        BGTipoSolicitud.add(BApelarSancion);
        BGTipoSolicitud.add(BSoporteSala);

        btnEnviar.addActionListener(e -> {
            if (!BApelarSancion.isSelected() && !BSoporteSala.isSelected()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de solicitud.");
                return;
            }

            String tipoSolicitud = BApelarSancion.isSelected() ? "Apelar Sancion" : "Soporte Sala";
            String observaciones = txtObservaciones.getText();

            // Ahora sí usamos el ID real del usuario
            Long idUsuario = usuarioActivo.getId();

            RealizarSoporte soporteLogic = new RealizarSoporte();
            try {
                boolean exito = soporteLogic.guardarSoporte(idUsuario, tipoSolicitud, observaciones);
                if (exito) {
                    JOptionPane.showMessageDialog(this,
                        "Soporte enviado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                    txtObservaciones.setText("");
                    BGTipoSolicitud.clearSelection();

                    // Actualizar la tabla en GestionarSoporteGUI
                    if (gestionarSoporteGUI != null) {
                        gestionarSoporteGUI.cargarDatosSoporte();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "No se pudo enviar el soporte.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al enviar soporte: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
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

        BGTipoSolicitud = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblPedirSoporte = new javax.swing.JLabel();
        lblTipoSolicitud = new javax.swing.JLabel();
        lblObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        BSoporteSala = new javax.swing.JRadioButton();
        btnEnviar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        BApelarSancion = new javax.swing.JRadioButton();
        btnSalir = new javax.swing.JButton();
        lblSoporte = new javax.swing.JLabel();
        lblFondoSoporte = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPedirSoporte.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblPedirSoporte.setForeground(new java.awt.Color(255, 255, 255));
        lblPedirSoporte.setText("PEDIR SOPORTE");
        jPanel1.add(lblPedirSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        lblTipoSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTipoSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoSolicitud.setText("TipoSolicitud");
        jPanel1.add(lblTipoSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 200, -1));

        lblObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblObservaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblObservaciones.setText("MENSAJE:");
        jPanel1.add(lblObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 200, -1));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        BSoporteSala.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BSoporteSala.setForeground(new java.awt.Color(255, 255, 255));
        BSoporteSala.setText("Soporte a Sala");
        jPanel1.add(BSoporteSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnviar.setText("ENVIAR");
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, 130, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 580, 160, 40));

        BApelarSancion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BApelarSancion.setForeground(new java.awt.Color(255, 255, 255));
        BApelarSancion.setText("Apelar Sancion");
        jPanel1.add(BApelarSancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, 130, 40));

        lblSoporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PedirSoporte.png"))); // NOI18N
        jPanel1.add(lblSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 280, 280));

        lblFondoSoporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoUsu.jpg"))); // NOI18N
        jPanel1.add(lblFondoSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JRadioButton BApelarSancion;
    private javax.swing.ButtonGroup BGTipoSolicitud;
    private javax.swing.JRadioButton BSoporteSala;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoSoporte;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblPedirSoporte;
    private javax.swing.JLabel lblSoporte;
    private javax.swing.JLabel lblTipoSolicitud;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables

}
