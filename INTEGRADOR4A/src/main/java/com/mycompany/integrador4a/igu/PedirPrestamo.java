/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador4a.igu;

import Interfaz.PlaceHolder;
import Interfaz.ActualizarFecha;
import Interfaz.HorarioAyuda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class PedirPrestamo extends javax.swing.JFrame {

    private DefaultTableModel model;
    
    String[] equiposAudiovisuales = { "Proyector", "Cámara de video", "Pantalla", "Micrófono", "Elegir otro" };
    String[] salasInformatica = { "Sala A - Diseño Gráfico", "Sala B - Edición de Video", "Sala C - Programación", "Elegir otro" };

    public PedirPrestamo() {
        initComponents();
        setLocationRelativeTo(null);

        PlaceHolder.aplicarPlaceholder(TextOtroMaterial, "Ingrese otro elemento");
        ActualizarFecha.llenarFechas(ElegirFecha);

        HoraInicio.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String horaInicio = (String) HoraInicio.getSelectedItem();
            HorarioAyuda.actualizarComboHoraFin(horaInicio, HoraFinal);
        }
    });

    
        String horaInicioDefault = (String) HoraInicio.getSelectedItem();
        HorarioAyuda.actualizarComboHoraFin(horaInicioDefault, HoraFinal);


        ButtonGroup grupoOpciones = new ButtonGroup();
        grupoOpciones.add(BEquipoAudiovisual);
        grupoOpciones.add(BSalaDeInformatica);

        BEquipoAudiovisual.addActionListener(e -> {
            actualizarComboOpciones(equiposAudiovisuales);
            TextOtroMaterial.setEnabled(false);
            TextOtroMaterial.setText("");
        });

        BSalaDeInformatica.addActionListener(e -> {
            actualizarComboOpciones(salasInformatica);
            TextOtroMaterial.setEnabled(false);
            TextOtroMaterial.setText("");
        });

        ElegirEquipo.addActionListener(e -> {
            String seleccion = (String) ElegirEquipo.getSelectedItem();
            if ("Elegir otro".equals(seleccion)) {
                TextOtroMaterial.setEnabled(true);
            } else {
                TextOtroMaterial.setEnabled(false);
                TextOtroMaterial.setText("");
            }
        });
        BEquipoAudiovisual.setSelected(true);
        actualizarComponentesSegunSeleccion();

        String[] columnas = {"Tipo de servicio", "Elemento seleccionado", "Eliminar"};
        model = new DefaultTableModel(columnas, 0);
        TablaVisual.setModel(model);

        TablaVisual.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        TablaVisual.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));


        jPanel1.setLayout(null);

        BAgregar.addActionListener(e -> agregarAFila());
    }

    private void actualizarComboOpciones(String[] opciones) {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(opciones);
        ElegirEquipo.setModel(modelo);
    }

    private void actualizarComponentesSegunSeleccion() {
        ElegirEquipo.removeAllItems();

        if (BEquipoAudiovisual.isSelected()) {
            for (String equipo : equiposAudiovisuales) {
                ElegirEquipo.addItem(equipo);
            }
            TextOtroMaterial.setEnabled(false);
        } else if (BSalaDeInformatica.isSelected()) {
            for (String sala : salasInformatica) {
                ElegirEquipo.addItem(sala);
            }
            TextOtroMaterial.setEnabled(false);
        }
    }

    private void agregarAFila() {
        String tipoServicio = BEquipoAudiovisual.isSelected() ? "Equipo Audiovisual" : "Sala de Informatica";
        String seleccion = (String) ElegirEquipo.getSelectedItem();

        if ("Elegir otro".equals(seleccion)) {
            seleccion = TextOtroMaterial.getText().trim();
            if (seleccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del otro material.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Validar única selección si es sala
        if ("Sala de Informatica".equals(tipoServicio) && model.getRowCount() > 0) {
            JOptionPane.showMessageDialog(this, "Solo puede seleccionar una sala de informática.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        model.addRow(new Object[]{tipoServicio, seleccion, "❌"});
    }

    // Clase interna para el botón de la "X" (Eliminación)
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("❌");
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int row;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("❌");
            button.addActionListener(e -> {
                if (table != null && row >= 0 && row < model.getRowCount()) {
                    fireEditingStopped(); // <- ¡Primero detenemos la edición!
                    model.removeRow(row); // <- Luego eliminamos
                }
            });
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblPrestamoSalas = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblTiempoDeUso = new javax.swing.JLabel();
        lblTipoMaterial = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnIrAudiovisual = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblSala1 = new javax.swing.JLabel();
        lblSala2 = new javax.swing.JLabel();
        lblSala3 = new javax.swing.JLabel();
        BEquipoAudiovisual = new javax.swing.JRadioButton();
        BSalaDeInformatica = new javax.swing.JRadioButton();
        HoraFinal = new javax.swing.JComboBox<>();
        HoraInicio = new javax.swing.JComboBox<>();
        ElegirFecha = new javax.swing.JComboBox<>();
        ElegirEquipo = new javax.swing.JComboBox<>();
        TextOtroMaterial = new javax.swing.JTextField();
        lblfecha2 = new javax.swing.JLabel();
        lblfecha1 = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVisual = new javax.swing.JTable();
        BAgregar = new javax.swing.JButton();
        lblFondoSala = new javax.swing.JLabel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrestamoSalas.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblPrestamoSalas.setForeground(new java.awt.Color(255, 255, 255));
        lblPrestamoSalas.setText("MIS PRESTAMOS");
        jPanel1.add(lblPrestamoSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        lblTipo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("Tipo:");
        jPanel1.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 140, -1));

        lblTiempoDeUso.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTiempoDeUso.setForeground(new java.awt.Color(255, 255, 255));
        lblTiempoDeUso.setText("Tiempo de uso:");
        jPanel1.add(lblTiempoDeUso, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 210, -1));

        lblTipoMaterial.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTipoMaterial.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoMaterial.setText("Tipo de equipo:");
        jPanel1.add(lblTipoMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 210, -1));

        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnviar.setText("ENVIAR");
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 130, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        btnMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 570, 150, 40));

        btnIrAudiovisual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIrAudiovisual.setText("AUDIOVISUAL");
        btnIrAudiovisual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrAudiovisualActionPerformed(evt);
            }
        });
        jPanel1.add(btnIrAudiovisual, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 150, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 570, 130, 40));

        lblSala1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hackerUsu.png"))); // NOI18N
        jPanel1.add(lblSala1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 130, 140));

        lblSala2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/disenoUsu.png"))); // NOI18N
        jPanel1.add(lblSala2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 390, 140, 130));

        lblSala3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/codigoUsu.png"))); // NOI18N
        jPanel1.add(lblSala3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 240, 130, 110));

        BEquipoAudiovisual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BEquipoAudiovisual.setForeground(new java.awt.Color(255, 255, 255));
        BEquipoAudiovisual.setText("Equipo Audiovisual");
        jPanel1.add(BEquipoAudiovisual, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        BSalaDeInformatica.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BSalaDeInformatica.setForeground(new java.awt.Color(255, 255, 255));
        BSalaDeInformatica.setText("Sala De Informatica");
        jPanel1.add(BSalaDeInformatica, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jPanel1.add(HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, 170, 40));

        HoraInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 Pm", "6:00 pm" }));
        jPanel1.add(HoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 170, 40));

        jPanel1.add(ElegirFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 170, 40));

        ElegirEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(ElegirEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 170, 40));

        TextOtroMaterial.setText("Otro");
        jPanel1.add(TextOtroMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 260, 40));

        lblfecha2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblfecha2.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha2.setText("Hora Final");
        jPanel1.add(lblfecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 440, -1, -1));

        lblfecha1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblfecha1.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha1.setText("Hora de Inicio");
        jPanel1.add(lblfecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, -1, -1));

        lblfecha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblfecha.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha.setText("Fecha");
        jPanel1.add(lblfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, -1, -1));

        TablaVisual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(TablaVisual);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 330, 170));

        BAgregar.setText("Agregar");
        jPanel1.add(BAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, -1, -1));

        lblFondoSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoUsu.jpg"))); // NOI18N
        jPanel1.add(lblFondoSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
        //Para ir al menu principal del usuario(Menu Usuario)
        MenuUsuario irMenu = new MenuUsuario();
        irMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnMenuPrincipalActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       //Boton para salir de la pestaña actual a el inicio(LOGIN)
        Login irInicio = new Login();
        irInicio.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIrAudiovisualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrAudiovisualActionPerformed

    }//GEN-LAST:event_btnIrAudiovisualActionPerformed

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
            java.util.logging.Logger.getLogger(PedirPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedirPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedirPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedirPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedirPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAgregar;
    private javax.swing.JRadioButton BEquipoAudiovisual;
    private javax.swing.JRadioButton BSalaDeInformatica;
    private javax.swing.JComboBox<String> ElegirEquipo;
    private javax.swing.JComboBox<String> ElegirFecha;
    private javax.swing.JComboBox<String> HoraFinal;
    private javax.swing.JComboBox<String> HoraInicio;
    private javax.swing.JTable TablaVisual;
    private javax.swing.JTextField TextOtroMaterial;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnIrAudiovisual;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoSala;
    private javax.swing.JLabel lblPrestamoSalas;
    private javax.swing.JLabel lblSala1;
    private javax.swing.JLabel lblSala2;
    private javax.swing.JLabel lblSala3;
    private javax.swing.JLabel lblTiempoDeUso;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipoMaterial;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblfecha1;
    private javax.swing.JLabel lblfecha2;
    // End of variables declaration//GEN-END:variables
}
