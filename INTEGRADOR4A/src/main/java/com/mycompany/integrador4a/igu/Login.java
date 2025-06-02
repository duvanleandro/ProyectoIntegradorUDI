
package com.mycompany.integrador4a.igu;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends javax.swing.JFrame { 


    public Login() {
    initComponents();
    setLocationRelativeTo(null);
    }

public JButton getBtnLogin() {
        return btnLogin;
    }

    // Devuelve el texto de txtCorreo
    public String getUsuario() {
        return txtCorreo.getText().trim();
    }

    // Cambiado para usar PContra en lugar de txtPassword
    public String getPassword() {
        return new String(PContra.getPassword());
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Bienvenidos = new javax.swing.JPanel();
        lblBienvenido = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblContra = new javax.swing.JLabel();
        PContra = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel_Bienvenidos.setPreferredSize(new java.awt.Dimension(1080, 640));
        Panel_Bienvenidos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenido.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblBienvenido.setText("BIENVENIDO");
        Panel_Bienvenidos.add(lblBienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        lblUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png"))); // NOI18N
        Panel_Bienvenidos.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 130, 150));

        lblCorreo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCorreo.setText("CORREO:");
        Panel_Bienvenidos.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, -1, -1));
        Panel_Bienvenidos.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 270, 30));

        lblContra.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblContra.setText("CONTRASEÑA:");
        Panel_Bienvenidos.add(lblContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, -1, -1));
        Panel_Bienvenidos.add(PContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 270, 30));

        btnLogin.setText("LOGIN");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Panel_Bienvenidos.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 130, 40));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Login3.jpg"))); // NOI18N
        Panel_Bienvenidos.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bienvenidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bienvenidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField PContra;
    private javax.swing.JPanel Panel_Bienvenidos;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblContra;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables

}
