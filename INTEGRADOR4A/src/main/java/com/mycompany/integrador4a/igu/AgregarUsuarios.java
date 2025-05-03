
package com.mycompany.integrador4a.igu;

import Conexion.ConexionOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AgregarUsuarios extends javax.swing.JFrame {

    ConexionOracle con = new ConexionOracle();
    Connection cn = con.conectar();
    
    public AgregarUsuarios() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Agregar usuarios");
    }
    
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarUsuarios().setVisible(true);
            }
        });
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGestionUsuarios = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        InsertarNombre = new javax.swing.JTextField();
        nombre = new javax.swing.JLabel();
        apellido = new javax.swing.JLabel();
        InsertarApellido = new javax.swing.JTextField();
        rol = new javax.swing.JLabel();
        InsertarContraseña = new javax.swing.JTextField();
        email = new javax.swing.JLabel();
        InsertarEmail = new javax.swing.JTextField();
        contraseña = new javax.swing.JLabel();
        TipoRol = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        lblFondoGestionUsuarios = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGestionUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblGestionUsuarios.setText("AGREGAR USUARIOS");
        jPanel1.add(lblGestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 560, 40));

        btnBorrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrar.setText("BORRAR");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("REGRESAR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, 170, 40));

        InsertarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LimitarTextoNombre(evt);
            }
        });
        jPanel1.add(InsertarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 160, -1));

        nombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nombre.setText("NOMBRE:");
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 90, 20));

        apellido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        apellido.setText("APELLIDO:");
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 110, 20));

        InsertarApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LimitarTextoApellido(evt);
            }
        });
        jPanel1.add(InsertarApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 160, -1));

        rol.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rol.setText("ROL:");
        jPanel1.add(rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 50, 20));

        InsertarContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LimitarTextoContraseña(evt);
            }
        });
        jPanel1.add(InsertarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 460, -1));

        email.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        email.setText("EMAIL:");
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 70, 20));

        InsertarEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LimitarTextoEmail(evt);
            }
        });
        jPanel1.add(InsertarEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 460, -1));

        contraseña.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        contraseña.setText("CONTRASEÑA:");
        jPanel1.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 140, 20));

        TipoRol.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TipoRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN" }));
        jPanel1.add(TipoRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 120, 40));

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, 170, 40));

        lblFondoGestionUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoGestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        GestionarUsuarios irUsuario = new GestionarUsuarios();
        irUsuario.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void LimitarTextoNombre(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LimitarTextoNombre
        if (InsertarNombre.getText().length() >= 20){
            evt.consume();
        }
    }//GEN-LAST:event_LimitarTextoNombre

    private void LimitarTextoApellido(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LimitarTextoApellido
        if (InsertarApellido.getText().length() >= 20) {
            evt.consume();
        }
    }//GEN-LAST:event_LimitarTextoApellido

    private void LimitarTextoEmail(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LimitarTextoEmail
        if (InsertarEmail.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_LimitarTextoEmail

    private void LimitarTextoContraseña(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LimitarTextoContraseña
        if (InsertarContraseña.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_LimitarTextoContraseña

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String nombre = InsertarNombre.getText();
        String apellido = InsertarApellido.getText();
        String email = InsertarEmail.getText();
        String clave = InsertarContraseña.getText();
        String nivel = (String) TipoRol.getSelectedItem();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || clave.isEmpty() || nivel.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
        } else {
            try {
               
                String sqlCode = "INSERT INTO usuarios (id, nombre, apellido, email, clave, nivel) VALUES (usuarios_id_seq.NEXTVAL, ?, ?, ?, ?, ?)";


                PreparedStatement ps = cn.prepareStatement(sqlCode);

            
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, email);
                ps.setString(4, clave);
                ps.setString(5, nivel);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null, "La cuenta ha sido registrada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al guardar los datos.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField InsertarApellido;
    private javax.swing.JTextField InsertarContraseña;
    private javax.swing.JTextField InsertarEmail;
    private javax.swing.JTextField InsertarNombre;
    private javax.swing.JComboBox<String> TipoRol;
    private javax.swing.JLabel apellido;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel contraseña;
    private javax.swing.JLabel email;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondoGestionUsuarios;
    private javax.swing.JLabel lblGestionUsuarios;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel rol;
    // End of variables declaration//GEN-END:variables
}
