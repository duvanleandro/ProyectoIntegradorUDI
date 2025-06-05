package com.mycompany.integrador4a.igu;

import Entidad.Usuario;
import Logica.RealizarEncuesta;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class RealizarEncuestaGUI extends javax.swing.JFrame {

    private Usuario usuarioActivo;  // Usuario logueado
    private RealizarEncuesta logicaEncuesta = new RealizarEncuesta();  // instancia lógica

    public RealizarEncuestaGUI(Usuario usuario) {
         initComponents();
    this.usuarioActivo = usuario;

    // Agregar radios a sus grupos para que solo uno pueda seleccionarse por pregunta
    GrupoP1.add(RtaP1);
    GrupoP1.add(RtaN1);

    GrupoP2.add(RtaP2);
    GrupoP2.add(RtaN2);

    GrupoP3.add(RtaP3);
    GrupoP3.add(RtaN3);

    btnEnviar.addActionListener(e -> enviarEncuesta());
    }

    private void enviarEncuesta() {
        // Validar que haya selección en cada grupo (pregunta)
        if (GrupoP1.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para la Pregunta 1");
            return;
        }
        if (GrupoP2.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para la Pregunta 2");
            return;
        }
        if (GrupoP3.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una opción para la Pregunta 3");
            return;
        }

        // Obtener respuestas ("bien" o "mal")
        String rta1 = RtaP1.isSelected() ? "bien" : "mal";
        String rta2 = RtaP2.isSelected() ? "bien" : "mal";
        String rta3 = RtaP3.isSelected() ? "bien" : "mal";

        // Guardar encuesta usando la lógica
        boolean exito = logicaEncuesta.guardarEncuesta(usuarioActivo, rta1, rta2, rta3);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Encuesta enviada correctamente");
            limpiarSeleccion();
        } else {
            JOptionPane.showMessageDialog(this, "Error al enviar la encuesta");
        }
    }

    private void limpiarSeleccion() {
        GrupoP1.clearSelection();
        GrupoP2.clearSelection();
        GrupoP3.clearSelection();
    }

    // Getters para botones (si los usas)
    public JButton getBtnMenuPrincipal() {
        return btnMenuPrincipal;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoP1 = new javax.swing.ButtonGroup();
        GrupoP2 = new javax.swing.ButtonGroup();
        GrupoP3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblPedirSoporte = new javax.swing.JLabel();
        Pregunta3 = new javax.swing.JLabel();
        Pregunta2 = new javax.swing.JLabel();
        Pregunta1 = new javax.swing.JLabel();
        RtaN1 = new javax.swing.JRadioButton();
        RtaP1 = new javax.swing.JRadioButton();
        RtaN2 = new javax.swing.JRadioButton();
        RtaP2 = new javax.swing.JRadioButton();
        RtaN3 = new javax.swing.JRadioButton();
        RtaP3 = new javax.swing.JRadioButton();
        btnEnviar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondoSoporte = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPedirSoporte.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblPedirSoporte.setForeground(new java.awt.Color(255, 255, 255));
        lblPedirSoporte.setText("ENCUESTA DEL SERVICIO");
        jPanel1.add(lblPedirSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        Pregunta3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Pregunta3.setForeground(new java.awt.Color(255, 255, 255));
        Pregunta3.setText("¿Esta encuesta le sirve de algo?");
        jPanel1.add(Pregunta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 720, -1));

        Pregunta2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Pregunta2.setForeground(new java.awt.Color(255, 255, 255));
        Pregunta2.setText("¿Los horarios registrados le sirvieron para enviar su prestamo?");
        jPanel1.add(Pregunta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 720, -1));

        Pregunta1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Pregunta1.setForeground(new java.awt.Color(255, 255, 255));
        Pregunta1.setText("¿Los equipos y programas utilizados fueron de su utilidad?");
        jPanel1.add(Pregunta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 660, -1));

        RtaN1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RtaN1.setForeground(new java.awt.Color(255, 255, 255));
        RtaN1.setText("Mal");
        jPanel1.add(RtaN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, -1, -1));

        RtaP1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RtaP1.setForeground(new java.awt.Color(255, 255, 255));
        RtaP1.setText("Bien");
        jPanel1.add(RtaP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        RtaN2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RtaN2.setForeground(new java.awt.Color(255, 255, 255));
        RtaN2.setText("Mal");
        jPanel1.add(RtaN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, -1, -1));

        RtaP2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RtaP2.setForeground(new java.awt.Color(255, 255, 255));
        RtaP2.setText("Bien");
        jPanel1.add(RtaP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        RtaN3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RtaN3.setForeground(new java.awt.Color(255, 255, 255));
        RtaN3.setText("Mal");
        jPanel1.add(RtaN3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, -1, -1));

        RtaP3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RtaP3.setForeground(new java.awt.Color(255, 255, 255));
        RtaP3.setText("Bien");
        jPanel1.add(RtaP3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, -1, -1));

        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnviar.setText("ENVIAR");
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, 130, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 580, 160, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, 130, 40));

        lblFondoSoporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoUsu.jpg"))); // NOI18N
        jPanel1.add(lblFondoSoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.ButtonGroup GrupoP1;
    private javax.swing.ButtonGroup GrupoP2;
    private javax.swing.ButtonGroup GrupoP3;
    private javax.swing.JLabel Pregunta1;
    private javax.swing.JLabel Pregunta2;
    private javax.swing.JLabel Pregunta3;
    private javax.swing.JRadioButton RtaN1;
    private javax.swing.JRadioButton RtaN2;
    private javax.swing.JRadioButton RtaN3;
    private javax.swing.JRadioButton RtaP1;
    private javax.swing.JRadioButton RtaP2;
    private javax.swing.JRadioButton RtaP3;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondoSoporte;
    private javax.swing.JLabel lblPedirSoporte;
    // End of variables declaration//GEN-END:variables

}
