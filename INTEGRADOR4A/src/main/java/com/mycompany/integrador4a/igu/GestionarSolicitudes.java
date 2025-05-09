// GestionarSolicitudes.java
package com.mycompany.integrador4a.igu;

import Interfaz.TablaSolicitudesAdmin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GestionarSolicitudes extends javax.swing.JFrame {

    public GestionarSolicitudes() {
        initComponents();
        setLocationRelativeTo(null);

        String[] columnas = {
            "Fecha","Hora inicio","Hora final",
            "Info Usuario","Acción","Aceptar","Rechazar","Elementos"
        };
        DefaultTableModel modelo = new DefaultTableModel(columnas,0);
        TablaSolicitudes.setModel(modelo);

        modelo.addRow(new Object[]{
            "2025-05-03","08:00","10:00",
            "Juan:Pérez:juan.perez@example.com",
            "Ver Detalles","✔️","❌",
            "Sala de informática:Sala A - Programación;Equipo audiovisual:Cámara"
        });
        modelo.addRow(new Object[]{
            "2025-05-04","14:00","16:00",
            "Ana:Gómez:ana.gomez@example.com",
            "Ver Detalles","✔️","❌",
            "Equipo audiovisual:Micrófono;Equipo audiovisual:Proyector"
        });

        TableColumn col=TablaSolicitudes.getColumnModel().getColumn(7);
        col.setMinWidth(0); col.setMaxWidth(0); col.setWidth(0);

        TablaSolicitudes.getColumnModel().getColumn(3)
            .setCellRenderer(new TablaSolicitudesAdmin.RenderBotonVerUsuario());
        TablaSolicitudes.getColumnModel().getColumn(3)
            .setCellEditor(new TablaSolicitudesAdmin.EditorBotonVerUsuario());

        TablaSolicitudes.getColumnModel().getColumn(4)
            .setCellRenderer(new TablaSolicitudesAdmin.RenderBoton("Ver Detalles"));
        TablaSolicitudes.getColumnModel().getColumn(4)
            .setCellEditor(new TablaSolicitudesAdmin.EditorBoton("Ver Detalles"));

        TablaSolicitudes.getColumnModel().getColumn(5)
            .setCellRenderer(new TablaSolicitudesAdmin.RenderBotonAceptar());
        TablaSolicitudes.getColumnModel().getColumn(5)
            .setCellEditor(new TablaSolicitudesAdmin.EditorBotonAceptar());

        TablaSolicitudes.getColumnModel().getColumn(6)
            .setCellRenderer(new TablaSolicitudesAdmin.RenderBotonRechazar());
        TablaSolicitudes.getColumnModel().getColumn(6)
            .setCellEditor(new TablaSolicitudesAdmin.EditorBotonRechazar());
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGestionSolicitud = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSolicitudes = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondoGestionSolicitud = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGestionSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblGestionSolicitud.setText("GESTION DE SOLICITUDES");
        jPanel1.add(lblGestionSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 640, 50));

        TablaSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablaSolicitudes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 231, 990, 320));

        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 170, 40));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, 170, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        btnMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 570, 170, 40));

        lblFondoGestionSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoGestionSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPrincipalActionPerformed
        // TODO add your handling code here:

        //Ir al menu principal del administrador
        MenuAdmin irAdmin = new MenuAdmin();
        irAdmin.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnMenuPrincipalActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:

        //Boton para salir de a la interfaz de LOGIN
        Login irInicio = new Login();
        irInicio.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(GestionarSolicitudes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarSolicitudes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarSolicitudes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarSolicitudes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarSolicitudes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaSolicitudes;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoGestionSolicitud;
    private javax.swing.JLabel lblGestionSolicitud;
    // End of variables declaration//GEN-END:variables
}
