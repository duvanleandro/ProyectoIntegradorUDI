/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador4a.igu;

/**
 *
 * @author Santiago
 */
public class InformacionApp extends javax.swing.JFrame {

    /**
     * Creates new form InformacionApp
     */
    public InformacionApp() {
        initComponents();
        setLocationRelativeTo(null);
        BloquearEscritura();
    }
    
    public void BloquearEscritura(){
        txtTextoInformacion.setEnabled(false);
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
        lblSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSolicitudMouseClicked(evt);
            }
        });
        jPanel1.add(lblSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 130, 150));

        lblTxtSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        lblTxtSolicitud.setText("REALIZAR SOLICITUD");
        jPanel1.add(lblTxtSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, -1));

        lblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salidaUsu.png"))); // NOI18N
        lblSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalirMouseClicked(evt);
            }
        });
        jPanel1.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, 150, 120));

        lblTextSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTextSalir.setForeground(new java.awt.Color(255, 255, 255));
        lblTextSalir.setText("CERRAR SESION");
        jPanel1.add(lblTextSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, -1, -1));

        lblMenuPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MenuPrincipal.png"))); // NOI18N
        lblMenuPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuPrincipalMouseClicked(evt);
            }
        });
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

    private void lblSolicitudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSolicitudMouseClicked
        // TODO add your handling code here:

        //Para ir a la interfaz de realizarSolicitud
        RealizarSolicitud irSolicitud = new RealizarSolicitud();
        irSolicitud.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblSolicitudMouseClicked

    private void lblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalirMouseClicked
        // TODO add your handling code here:

        //Para salir a la interfaz de Login
        Login Salir = new Login();//objeto
        Salir.setVisible(true);
        dispose();//para cerrar las otras ventanas si estan abiertas, que solo trabaje con una
    }//GEN-LAST:event_lblSalirMouseClicked

    private void lblMenuPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuPrincipalMouseClicked
        // TODO add your handling code here:
        
        //Para ir al menu principal del Usuario
        MenuUsuario irMenu = new MenuUsuario();
        irMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblMenuPrincipalMouseClicked

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
            java.util.logging.Logger.getLogger(InformacionApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionApp().setVisible(true);
            }
        });
    }

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
