/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.integrador4a.igu;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author USER
 */
public class RealizarSolicitud extends javax.swing.JFrame {

    /** Creates new form RealizarSolicitud */
    public RealizarSolicitud() {
        initComponents();
    }

    public JButton getBtnMenuPrincipal() {
        return btnMenuPrincipal;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JLabel getLblPrestarAudio() {
        return lblPrestarAudio;
    }

    public JLabel getLblPrestarSala() {
        return lblPrestarSala;
    }

    public JLabel getLblVerPrestamos() {
        return lblVerPrestamos;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblSolicitarPrestamo = new javax.swing.JLabel();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblVerPrestamos = new javax.swing.JLabel();
        lblPrestarAudio = new javax.swing.JLabel();
        lblPrestarSala = new javax.swing.JLabel();
        lblTextAudio = new javax.swing.JLabel();
        lblTextSala = new javax.swing.JLabel();
        lblFondoSolicitud = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSolicitarPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblSolicitarPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        lblSolicitarPrestamo.setText("SOLICITAR PRESTAMO");
        jPanel1.add(lblSolicitarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 550, 60));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 160, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 560, 130, 40));

        lblVerPrestamos.setText("jLabel2");
        lblVerPrestamos.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(lblVerPrestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 140, 160));

        lblPrestarAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/audiovisualUsu.png"))); // NOI18N
        jPanel1.add(lblPrestarAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 150, 150));

        lblPrestarSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SalaUsu.png"))); // NOI18N
        jPanel1.add(lblPrestarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 170, 150));

        lblTextAudio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTextAudio.setForeground(new java.awt.Color(255, 255, 255));
        lblTextAudio.setText("PEDIR UN PRESTAMO");
        jPanel1.add(lblTextAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, -1, -1));

        lblTextSala.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTextSala.setForeground(new java.awt.Color(255, 255, 255));
        lblTextSala.setText("VER MIS PRESTAMOS");
        jPanel1.add(lblTextSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, -1, -1));

        lblFondoSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoUsu.jpg"))); // NOI18N
        jPanel1.add(lblFondoSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondoSolicitud;
    private javax.swing.JLabel lblPrestarAudio;
    private javax.swing.JLabel lblPrestarSala;
    private javax.swing.JLabel lblSolicitarPrestamo;
    private javax.swing.JLabel lblTextAudio;
    private javax.swing.JLabel lblTextSala;
    private javax.swing.JLabel lblVerPrestamos;
    // End of variables declaration//GEN-END:variables

}
