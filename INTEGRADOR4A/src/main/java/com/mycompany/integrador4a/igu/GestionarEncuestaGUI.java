/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.mycompany.integrador4a.igu;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class GestionarEncuestaGUI extends javax.swing.JFrame {

    /** Creates new form RealizarSancion */
    public GestionarEncuestaGUI() {
        initComponents();
        cargarEncuestasEnTabla();
    }
    
    public void cargarEncuestasEnTabla() {
    Logica.GestionarEncuesta ge = new Logica.GestionarEncuesta();
    List<Entidad.Encuesta> lista = ge.obtenerTodasLasEncuestas();

    String[] columnas = {"ID encuesta", "Id usuario", "Pregunta 1", "Pregunta 2", "Pregunta 3"};

    Object[][] datos = new Object[lista.size()][columnas.length];

    for (int i = 0; i < lista.size(); i++) {
        Entidad.Encuesta e = lista.get(i);
        datos[i][0] = e.getId();    // Ajusta el nombre del método si es distinto
        datos[i][1] = e.getUsuario().getId();     // Ajusta el nombre del método si es distinto
        datos[i][2] = e.getPregunta1();     // Ajusta el nombre del método si es distinto
        datos[i][3] = e.getPregunta2();     // Ajusta el nombre del método si es distinto
        datos[i][4] = e.getPregunta3();     // Ajusta el nombre del método si es distinto
    }

    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(datos, columnas) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    VerEncuesta.setModel(modelo);
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
        VerEncuestas = new javax.swing.JScrollPane();
        VerEncuesta = new javax.swing.JTable();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondoSanciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSanciones.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblSanciones.setText("ENCUESTAS REGISTRADAS");
        jPanel1.add(lblSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 720, 40));

        VerEncuesta.setModel(new javax.swing.table.DefaultTableModel(
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
        VerEncuestas.setViewportView(VerEncuesta);

        jPanel1.add(VerEncuestas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 830, 380));

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
    private javax.swing.JTable VerEncuesta;
    private javax.swing.JScrollPane VerEncuestas;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondoSanciones;
    private javax.swing.JLabel lblSanciones;
    // End of variables declaration//GEN-END:variables

}
