/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.integrador4a.igu;

import Logica.BaseDeDatos;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class AdministrarBD extends javax.swing.JFrame {

    /** Creates new form RealizarSancion */
    public AdministrarBD() {
        initComponents();
        btnReiniciarBD.addActionListener(e -> {
    try {
        Conexion.ConexionOracle conexionOracle = new Conexion.ConexionOracle();
        Connection conn = conexionOracle.conectar();
        BaseDeDatos.reiniciarBaseDeDatos(conn);
        conexionOracle.cerrarConexion();

        JOptionPane.showMessageDialog(null, "Base de datos reiniciada correctamente.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al reiniciar BD:\n" + ex.getMessage());
        ex.printStackTrace();
    }
});

    }

    public JButton getBtnReiniciarBD() {
        return btnReiniciarBD;
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
        lblSanciones = new javax.swing.JLabel();
        btnReiniciarBD = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondoSanciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSanciones.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblSanciones.setText("ADMINISTRAR BASE DE DATOS");
        jPanel1.add(lblSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 720, 40));

        btnReiniciarBD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReiniciarBD.setText("REINICIAR BASE DE DATOS");
        jPanel1.add(btnReiniciarBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, 240, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 210, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 590, 130, 40));

        lblFondoSanciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JButton btnReiniciarBD;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondoSanciones;
    private javax.swing.JLabel lblSanciones;
    // End of variables declaration//GEN-END:variables

}
