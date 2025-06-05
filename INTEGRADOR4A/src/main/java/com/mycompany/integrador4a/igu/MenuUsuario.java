package com.mycompany.integrador4a.igu;

import Entidad.Usuario;
import Logica.OtrosFuncionamientos;
import javax.swing.JLabel;

public class MenuUsuario extends javax.swing.JFrame {

    private Usuario usuario;  // atributo para guardar el usuario actual

    /** Creates new form MenuUsuario */
    public MenuUsuario(Usuario usuario) {
        this.usuario = usuario; // guardamos el usuario recibido
        initComponents();
        OtrosFuncionamientos.pintar(this.lblEncuesta, "/images/GestionarEncuesta.png");
    }

    public JLabel getLblEncuesta() {
        return lblEncuesta;
    }

    public JLabel getLblInformacion() {
        return lblInformacion;
    }

    public JLabel getLblSalir() {
        return lblSalir;
    }

    public JLabel getLblSolicitud() {
        return lblSolicitud;
    }

    public JLabel getLblSoporte() {
        return lblSoporte;
    }
    
    public JLabel getLblMisSanciones() {
        return lblMisSanciones;
    }
    
        public Long getIdUsuario() {
        return usuario.getId();
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPanelUsuario = new javax.swing.JLabel();
        lblEncuesta = new javax.swing.JLabel();
        lblTextDevolucion = new javax.swing.JLabel();
        lblSoporte = new javax.swing.JLabel();
        txtSoporte = new javax.swing.JLabel();
        lblSolicitud = new javax.swing.JLabel();
        lblTxtSolicitud = new javax.swing.JLabel();
        lblInformacion = new javax.swing.JLabel();
        lblTxtInformacion = new javax.swing.JLabel();
        lblMisSanciones = new javax.swing.JLabel();
        lblTxtMisSanciones = new javax.swing.JLabel();
        lblSalir = new javax.swing.JLabel();
        lblTextSalir = new javax.swing.JLabel();
        lblFondoUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPanelUsuario.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblPanelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblPanelUsuario.setText("PANEL USUARIO");
        jPanel1.add(lblPanelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));
        jPanel1.add(lblEncuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 130, 140));

        lblTextDevolucion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTextDevolucion.setForeground(new java.awt.Color(255, 255, 255));
        lblTextDevolucion.setText("ENCUESTA");
        jPanel1.add(lblTextDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, -1, -1));

        lblSoporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/soporteUsu.png"))); // NOI18N
        jPanel1.add(lblSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 170, 130));

        txtSoporte.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoporte.setForeground(new java.awt.Color(255, 255, 255));
        txtSoporte.setText("SOPORTE");
        jPanel1.add(txtSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 320, -1, -1));

        lblSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/solicitudUsu.png"))); // NOI18N
        jPanel1.add(lblSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 130, 150));

        lblTxtSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        lblTxtSolicitud.setText("REALIZAR SOLICITUD");
        jPanel1.add(lblTxtSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, -1));

        lblInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/InfoUsu.png"))); // NOI18N
        jPanel1.add(lblInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 130, 130));

        lblTxtInformacion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtInformacion.setForeground(new java.awt.Color(255, 255, 255));
        lblTxtInformacion.setText("INFORMACION");
        jPanel1.add(lblTxtInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, -1, -1));

        lblMisSanciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sancionAdmin.png"))); // NOI18N
        jPanel1.add(lblMisSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 130, 130));

        lblTxtMisSanciones.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtMisSanciones.setForeground(new java.awt.Color(255, 255, 255));
        lblTxtMisSanciones.setText("MIS SANCIONES");
        jPanel1.add(lblTxtMisSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 570, -1, -1));

        lblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salidaUsu.png"))); // NOI18N
        jPanel1.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 430, 150, 120));

        lblTextSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTextSalir.setForeground(new java.awt.Color(255, 255, 255));
        lblTextSalir.setText("CERRAR SESION");
        jPanel1.add(lblTextSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, -1, -1));

        lblFondoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoUsu.jpg"))); // NOI18N
        jPanel1.add(lblFondoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEncuesta;
    private javax.swing.JLabel lblFondoUsuario;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lblMisSanciones;
    private javax.swing.JLabel lblPanelUsuario;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JLabel lblSolicitud;
    private javax.swing.JLabel lblSoporte;
    private javax.swing.JLabel lblTextDevolucion;
    private javax.swing.JLabel lblTextSalir;
    private javax.swing.JLabel lblTxtInformacion;
    private javax.swing.JLabel lblTxtMisSanciones;
    private javax.swing.JLabel lblTxtSolicitud;
    private javax.swing.JLabel txtSoporte;
    // End of variables declaration//GEN-END:variables

    public Object getLblSancionar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getLblDevoluciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
