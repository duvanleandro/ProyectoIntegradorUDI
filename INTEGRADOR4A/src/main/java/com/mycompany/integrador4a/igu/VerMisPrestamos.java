// com/mycompany/integrador4a/igu/VerMisPrestamos.java
package com.mycompany.integrador4a.igu;

import Interfaz.TablaMisPrestamos;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

public class VerMisPrestamos extends javax.swing.JFrame {

    public VerMisPrestamos() {
        initComponents();
        setLocationRelativeTo(null);

        String[] columnas = {
            "Fecha", "Hora inicio", "Hora final",
            "Estado", "Acción", "Cancelar"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        TablaMisPrestamos.setModel(modelo);

        TablaMisPrestamos.getColumnModel().getColumn(4)
            .setCellRenderer(new TablaMisPrestamos.RenderizadorBoton("Ver Detalles"));
        TablaMisPrestamos.getColumnModel().getColumn(4)
            .setCellEditor(new TablaMisPrestamos.EditorBoton(new JCheckBox(), "Ver Detalles"));

        TablaMisPrestamos.getColumnModel().getColumn(5)
            .setCellRenderer(new TablaMisPrestamos.RenderizadorCancelar());
        TablaMisPrestamos.getColumnModel().getColumn(5)
            .setCellEditor(new TablaMisPrestamos.EditorCancelar(new JCheckBox()));

        modelo.addRow(new Object[]{
            "2025-05-03", "08:00", "10:00", "Pendiente", "Ver Detalles", "❌"
        });
        modelo.addRow(new Object[]{
            "2025-05-04", "14:00", "16:00", "Aceptado", "Ver Detalles", "❌"
        });
        modelo.addRow(new Object[]{
            "2025-05-05", "09:00", "11:00", "Rechazado", "Ver Detalles", "❌"
        });
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new VerMisPrestamos().setVisible(true);
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblSolicitarPrestamo = new javax.swing.JLabel();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMisPrestamos = new javax.swing.JTable();
        BHistorial = new javax.swing.JRadioButton();
        lblFondoSolicitud = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSolicitarPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblSolicitarPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        lblSolicitarPrestamo.setText("MIS PRESTAMOS");
        jPanel1.add(lblSolicitarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 390, 60));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        btnMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, 160, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 560, 130, 40));

        TablaMisPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Hora Inicio", "Hora Final", "Tipo Servicio", "Elemento reservado", "Estado", "Acción"
            }
        ));
        jScrollPane1.setViewportView(TablaMisPrestamos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 860, 200));

        BHistorial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BHistorial.setForeground(new java.awt.Color(255, 255, 255));
        BHistorial.setText("Historial de solicitudes");
        jPanel1.add(BHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, -1, -1));

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

    private void btnMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPrincipalActionPerformed
        // TODO add your handling code here:
        
        //Ir a la interfaz del menu principla(MenuUsuario)
        MenuUsuario irMenu = new MenuUsuario();
        irMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnMenuPrincipalActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Login irInicio = new Login();
        irInicio.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BHistorial;
    private javax.swing.JTable TablaMisPrestamos;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoSolicitud;
    private javax.swing.JLabel lblSolicitarPrestamo;
    // End of variables declaration//GEN-END:variables
}
