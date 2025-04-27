package com.mycompany.integrador4a.igu;

import Conexion.ConexionOracle;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class Login extends javax.swing.JFrame {

    ConexionOracle con = new ConexionOracle();
    Connection cn = con.conectar();
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Login");
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

        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoKeyPressed(evt);
            }
        });
        Panel_Bienvenidos.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 270, 30));

        lblContra.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblContra.setText("CONTRASEÑA:");
        Panel_Bienvenidos.add(lblContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, -1, -1));

        PContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PContraKeyPressed(evt);
            }
        });
        Panel_Bienvenidos.add(PContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 270, 30));

        btnLogin.setText("LOGIN");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
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
/* private void realizarLogin() {
    String correo = txtCorreo.getText();
    String contraseña = new String(PContra.getPassword()); // Obtener la contraseña de forma segura

    if (correo.isEmpty() || contraseña.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Un campo está vacío");
    } else {
        if (correo.equals("usuario@example.com") && contraseña.equals("usuario")) {
            JOptionPane.showMessageDialog(null, "Bienvenido Usuario");
            MenuUsuario MenuUsu = new MenuUsuario();
            MenuUsu.setVisible(true);
            this.dispose();
        } else if (correo.equals("admin@example.com") && contraseña.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
            MenuAdmin MenuAdm = new MenuAdmin();
            MenuAdm.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "El usuario o contraseña es incorrecto o no existe");
        }
    }
}
*/
    
    private void realizarLogin() {
    String correo = txtCorreo.getText();
    String contraseña = new String(PContra.getPassword()); 
    
    if (correo.equals("") || contraseña.equals("")) {
        JOptionPane.showMessageDialog(null, "Los campos están vacios");
    } else {
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT nivel FROM usuarios WHERE email=? AND clave=?");
            ps.setString(1, correo); // Reemplaza el primer "?" con el valor de la variable "correo"
            ps.setString(2, contraseña);  // Reemplaza el segundo "?" con el valor de la variable "clave"
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String TipoNivel = rs.getString("nivel");
                if (TipoNivel.equalsIgnoreCase("ADMIN")) {
                    JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                    MenuAdmin MenuAdm = new MenuAdmin();
                    MenuAdm.setVisible(true);
                    dispose();  // Cierra la ventana actual
                    // Aquí puedes abrir la ventana de admin
                } else {
                    JOptionPane.showMessageDialog(null, "Bienvenido Usuario");
                    MenuUsuario MenuUsu = new MenuUsuario();
                    MenuUsu.setVisible(true);
                    dispose();
                    // Aquí puedes abrir la ventana de usuario
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario o contraseña esta incorrecto o no existe");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
        }
    }
}

    
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
    realizarLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void PContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PContraKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            realizarLogin();
        }
    }//GEN-LAST:event_PContraKeyPressed

    private void txtCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            PContra.requestFocus();
        }
    }//GEN-LAST:event_txtCorreoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

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
