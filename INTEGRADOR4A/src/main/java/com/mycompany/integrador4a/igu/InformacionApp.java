/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.integrador4a.igu;

import javax.swing.JLabel;

/**
 *
 * @author USER
 */
public class InformacionApp extends javax.swing.JFrame {

    /** Creates new form InformacionApp */
    public InformacionApp() {
        initComponents();
    }

    public JLabel getLblMenuPrincipal() {
        return lblMenuPrincipal;
    }

    public JLabel getLblSalir() {
        return lblSalir;
    }

    public JLabel getLblSolicitud() {
        return lblSolicitud;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblInformacion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTextoInformacion = new javax.swing.JTextArea();
        lblSolicitud = new javax.swing.JLabel();
        lblTxtSolicitud = new javax.swing.JLabel();
        lblSalir = new javax.swing.JLabel();
        lblTextSalir = new javax.swing.JLabel();
        lblMenuPrincipal = new javax.swing.JLabel();
        lblTxtMenuPrincipal = new javax.swing.JLabel();
        lblFondoInformacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInformacion.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblInformacion.setForeground(new java.awt.Color(255, 255, 255));
        lblInformacion.setText("INFORMACION");
        jPanel1.add(lblInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        txtTextoInformacion.setColumns(20);
        txtTextoInformacion.setFont(new java.awt.Font("Yu Gothic Medium", 1, 24)); // NOI18N
        txtTextoInformacion.setRows(5);
        txtTextoInformacion.setText("Hola Bienvenido, este es el Software de Prestamo de Salas de informatica\nY Equipos Audiovisuales de la universidad.\n\nSi desea Hacer un prestamo de una sala o audiovisual debera dirigirse a \nel icono de Realizar solicitud.\n\nAqui dejamos un acceso rapido para mayor facilidad. Buen dia.");
        jScrollPane1.setViewportView(txtTextoInformacion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 910, 320));

        lblSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/solicitudUsu.png"))); // NOI18N
        jPanel1.add(lblSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 130, 150));

        lblTxtSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        lblTxtSolicitud.setText("REALIZAR SOLICITUD");
        jPanel1.add(lblTxtSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, -1));

        lblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salidaUsu.png"))); // NOI18N
        jPanel1.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, 150, 120));

        lblTextSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTextSalir.setForeground(new java.awt.Color(255, 255, 255));
        lblTextSalir.setText("CERRAR SESION");
        jPanel1.add(lblTextSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, -1, -1));

        lblMenuPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MenuPrincipal.png"))); // NOI18N
        jPanel1.add(lblMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 130, 120));

        lblTxtMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtMenuPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTxtMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(lblTxtMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 580, 170, -1));

        lblFondoInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoUsu.jpg"))); // NOI18N
        jPanel1.add(lblFondoInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1060, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoInformacion;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lblMenuPrincipal;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JLabel lblSolicitud;
    private javax.swing.JLabel lblTextSalir;
    private javax.swing.JLabel lblTxtMenuPrincipal;
    private javax.swing.JLabel lblTxtSolicitud;
    private javax.swing.JTextArea txtTextoInformacion;
    // End of variables declaration//GEN-END:variables

}
