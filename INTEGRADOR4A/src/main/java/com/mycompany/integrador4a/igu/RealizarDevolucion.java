/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.integrador4a.igu;

import javax.swing.JButton;

/**
 *
 * @author USER
 */
public class RealizarDevolucion extends javax.swing.JFrame {

    /** Creates new form RealizarDevolucion */
    public RealizarDevolucion() {
        initComponents();
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
        lblRealizarDevolucion = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblHora = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        lblCapacidad = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        lblObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblDevolucion1 = new javax.swing.JLabel();
        lblDevolucion2 = new javax.swing.JLabel();
        lblDevolucione3 = new javax.swing.JLabel();
        lblFondoDevolucion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRealizarDevolucion.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblRealizarDevolucion.setForeground(new java.awt.Color(255, 255, 255));
        lblRealizarDevolucion.setText("REALIZAR DEVOLUCION");
        jPanel1.add(lblRealizarDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 550, 70));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha:");
        jPanel1.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 140, -1));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 230, 30));

        lblHora.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("Hora:");
        jPanel1.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 140, -1));
        jPanel1.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 230, 30));

        lblCapacidad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCapacidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacidad.setText("Capacidad:");
        jPanel1.add(lblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 140, -1));
        jPanel1.add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 230, 30));

        lblObservaciones.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblObservaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblObservaciones.setText("Observaciones:");
        jPanel1.add(lblObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 200, -1));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, -1));

        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnviar.setText("ENVIAR");
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 590, 130, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 590, 150, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 590, 130, 40));

        lblDevolucion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DevolucionForm.png"))); // NOI18N
        jPanel1.add(lblDevolucion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, 250, 260));

        lblDevolucion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DevolucionForm2.png"))); // NOI18N
        jPanel1.add(lblDevolucion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 270, 250));

        lblDevolucione3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DevolucionesReloj.png"))); // NOI18N
        jPanel1.add(lblDevolucione3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 130, 120));

        lblFondoDevolucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoUsu.jpg"))); // NOI18N
        jPanel1.add(lblFondoDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblDevolucion1;
    private javax.swing.JLabel lblDevolucion2;
    private javax.swing.JLabel lblDevolucione3;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFondoDevolucion;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblRealizarDevolucion;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables

}
