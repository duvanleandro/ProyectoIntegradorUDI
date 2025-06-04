/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.integrador4a.igu;

import Logica.OtrosFuncionamientos;
import javax.swing.JLabel;

public class MenuAdmin extends javax.swing.JFrame {

    public MenuAdmin() {
        initComponents();
        OtrosFuncionamientos.pintar(this.lblBaseDeDatos,"/images/BaseDeDatos.png");
    }

    public JLabel getLblDevoluciones() {
        return lblDevoluciones;
    }

    public JLabel getLblSalir() {
        return lblSalir;
    }

    public JLabel getLblBaseDeDatos() {
        return lblBaseDeDatos;
    }

    public JLabel getLblSolicitudes() {
        return lblSolicitudes;
    }

    public JLabel getLblUsuarios() {
        return lblUsuarios;
    }
    public JLabel getLblGestionSanciones() {
            return lblGestionSanciones;
        }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPanelAdmin = new javax.swing.JLabel();
        lblSolicitudes = new javax.swing.JLabel();
        lblTxtSolicitudes = new javax.swing.JLabel();
        lblBaseDeDatos = new javax.swing.JLabel();
        lblTxtBaseDeDatos = new javax.swing.JLabel();
        lblUsuarios = new javax.swing.JLabel();
        lblTxtUsuarios = new javax.swing.JLabel();
        lblDevoluciones = new javax.swing.JLabel();
        lblGestionDevoluciones = new javax.swing.JLabel();
        lblGestionSanciones = new javax.swing.JLabel();
        lblTxtSanciones = new javax.swing.JLabel();
        lblSalir = new javax.swing.JLabel();
        lblTxtSalir = new javax.swing.JLabel();
        lblFondoAdmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPanelAdmin.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblPanelAdmin.setText("PANEL ADMINISTRADOR");
        jPanel1.add(lblPanelAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        lblSolicitudes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/solicitudUsu.png"))); // NOI18N
        jPanel1.add(lblSolicitudes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 140, 140));

        lblTxtSolicitudes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtSolicitudes.setText("GESTIONAR SOLICITUDES");
        jPanel1.add(lblTxtSolicitudes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));
        jPanel1.add(lblBaseDeDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 150, 140));

        lblTxtBaseDeDatos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtBaseDeDatos.setText("BASE DE DATOS");
        jPanel1.add(lblTxtBaseDeDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, -1, -1));

        lblUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gestionAdmin.png"))); // NOI18N
        jPanel1.add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 130, 160));

        lblTxtUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtUsuarios.setText("GESTIONAR USUARIOS");
        jPanel1.add(lblTxtUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, -1));

        lblDevoluciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/devolucionUsu.png"))); // NOI18N
        jPanel1.add(lblDevoluciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 140, 150));

        lblGestionDevoluciones.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblGestionDevoluciones.setText("GESTION DEVOLUCIONES");
        jPanel1.add(lblGestionDevoluciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, -1, -1));

        lblGestionSanciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sancion2admin.png"))); // NOI18N
        jPanel1.add(lblGestionSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 130, 150));

        lblTxtSanciones.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtSanciones.setText("GESTIONAR SANCIONES");
        jPanel1.add(lblTxtSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 570, 250, 30));

        lblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salidaUsu.png"))); // NOI18N
        jPanel1.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 450, 130, 130));

        lblTxtSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtSalir.setText("CERRAR SESION");
        jPanel1.add(lblTxtSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 590, -1, -1));

        lblFondoAdmin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblFondoAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 660));

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
    private javax.swing.JLabel lblBaseDeDatos;
    private javax.swing.JLabel lblDevoluciones;
    private javax.swing.JLabel lblFondoAdmin;
    private javax.swing.JLabel lblGestionDevoluciones;
    private javax.swing.JLabel lblGestionSanciones;
    private javax.swing.JLabel lblPanelAdmin;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JLabel lblSolicitudes;
    private javax.swing.JLabel lblTxtBaseDeDatos;
    private javax.swing.JLabel lblTxtSalir;
    private javax.swing.JLabel lblTxtSanciones;
    private javax.swing.JLabel lblTxtSolicitudes;
    private javax.swing.JLabel lblTxtUsuarios;
    private javax.swing.JLabel lblUsuarios;
    // End of variables declaration//GEN-END:variables

}
