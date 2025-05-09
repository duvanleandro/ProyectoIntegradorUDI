package com.mycompany.integrador4a.igu;

import Conexion.ConexionOracle;
import Interfaz.TablaMisPrestamos;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VerMisPrestamos extends JFrame {
    private final int userId;
    private DefaultTableModel modelo;

    public VerMisPrestamos(int userId) {
        this.userId = userId;
        initComponents();
        setLocationRelativeTo(null);
        configurarTabla();
        cargarSolicitudes();
    }

    private void configurarTabla() {
        // Definimos columnas incluyendo ID_SOLICITUD oculto
        String[] columnas = {"ID_SOLICITUD", "Fecha", "Hora inicio", "Hora final", "Estado", "Acción", "Cancelar"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo las columnas de acción y cancelar son editables
                return column == 5 || column == 6;
            }
        };
        TablaMisPrestamos.setModel(modelo);

        // Ocultar columna de ID_SOLICITUD
        TablaMisPrestamos.removeColumn(TablaMisPrestamos.getColumnModel().getColumn(0));

        // Renderers y editors
        TablaMisPrestamos.getColumnModel().getColumn(4)
            .setCellRenderer(new TablaMisPrestamos.RenderizadorBoton("Ver Detalles"));
        TablaMisPrestamos.getColumnModel().getColumn(4)
            .setCellEditor(new TablaMisPrestamos.EditorBoton(new JCheckBox(), "Ver Detalles", this));

        TablaMisPrestamos.getColumnModel().getColumn(5)
            .setCellRenderer(new TablaMisPrestamos.RenderizadorCancelar());
        TablaMisPrestamos.getColumnModel().getColumn(5)
            .setCellEditor(new TablaMisPrestamos.EditorCancelar(new JCheckBox()));
    }

    private void cargarSolicitudes() {
        ConexionOracle co = new ConexionOracle();
        try (Connection cn = co.conectar()) {
            String sql = "SELECT ID_SOLICITUD, FECHA_USO, HORA_INICIO, HORA_FIN, ESTADO " +
                         "FROM SOLICITUDES WHERE ID_USUARIO = ? ORDER BY FECHA_SOLICITUD DESC";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("ID_SOLICITUD"),
                    rs.getDate("FECHA_USO"),
                    rs.getString("HORA_INICIO"),
                    rs.getString("HORA_FIN"),
                    rs.getString("ESTADO"),
                    "Ver Detalles",
                    "❌"
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar solicitudes: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            co.cerrarConexion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // reemplaza 1 por el ID real del usuario autenticado
            new VerMisPrestamos(1).setVisible(true);
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
    private javax.swing.JTable TablaMisPrestamos;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoSolicitud;
    private javax.swing.JLabel lblSolicitarPrestamo;
    // End of variables declaration//GEN-END:variables
}
