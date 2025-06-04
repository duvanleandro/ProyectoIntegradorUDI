package com.mycompany.integrador4a.igu;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import Logica.RealizarSancionAdmin;


/**
 * JFrame para registrar sanciones.
 * Solo maneja GUI y validaciones básicas.
 * La lógica de conexión y persistencia debe estar en otra clase.
 */
public class RealizarSancion extends javax.swing.JFrame {

    /** Creates new form RealizarSancion */
    public RealizarSancion() {
        initComponents();
        inicializarComponentesPersonalizados();
    }

    private void inicializarComponentesPersonalizados() {
        // Llenar JComboBox con tipos de sanción
        TipoSancion.addItem("Daño a ordenador");
        TipoSancion.addItem("Mal uso de salas");
        TipoSancion.addItem("No se ha pagado la deuda");
        TipoSancion.addItem("Incumplimiento de salas");
        TipoSancion.setSelectedIndex(-1); // sin selección por defecto

        // Filtro para que txtIdUsuarioASancionar acepte solo números
        ((AbstractDocument) txtIdUsuarioASancionar.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // Listener para botón enviar, solo valida y muestra mensaje
        btnEnviar.addActionListener(e -> {
    if (validarFormulario()) {
        SancionDTO datos = getDatosSancion();
        Logica.RealizarSancionAdmin gestor = new Logica.RealizarSancionAdmin();

        boolean exito = gestor.guardarSancion(datos);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Sanción registrada con éxito.");
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la sanción. Verifique si el usuario existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        gestor.cerrar(); // Cierra el EntityManagerFactory
    }
});

    }

    // Validaciones básicas de campos (sin conexión)
    private boolean validarFormulario() {
        if (txtIdUsuarioASancionar.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el ID del usuario a sancionar.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (TipoSancion.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de sanción.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtMotivo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un motivo o mensaje para la sanción.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Long.parseLong(txtIdUsuarioASancionar.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID del usuario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Obtener datos del formulario para que otra clase maneje la lógica
    public SancionDTO getDatosSancion() {
        return new SancionDTO(
                Long.parseLong(txtIdUsuarioASancionar.getText().trim()),
                (String) TipoSancion.getSelectedItem(),
                txtMotivo.getText().trim()
        );
    }

    // Método para limpiar el formulario
    public void limpiarFormulario() {
        txtIdUsuarioASancionar.setText("");
        TipoSancion.setSelectedIndex(-1);
        txtMotivo.setText("");
    }

    // Getters para botones si los necesitas
    public JButton getBtnMenuPrincipal() {
        return btnMenuPrincipal;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    // Clase DTO para pasar datos de sanción fuera de la GUI
    public static class SancionDTO {
        public Long idUsuario;
        public String tipoSancion;
        public String motivo;

        public SancionDTO(Long idUsuario, String tipoSancion, String motivo) {
            this.idUsuario = idUsuario;
            this.tipoSancion = tipoSancion;
            this.motivo = motivo;
        }
    }
    
    // Aquí iría el método initComponents() generado por NetBeans o el constructor
    // donde inicialices txtIdUsuarioASancionar, TipoSancion, txtMotivo, btnGuardar, btnMenuPrincipal, btnSalir, etc.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblSanciones = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtIdUsuarioASancionar = new javax.swing.JTextField();
        TipoSancion = new javax.swing.JComboBox<>();
        lblFecha = new javax.swing.JLabel();
        lblMotivo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblSancionImagen = new javax.swing.JLabel();
        lblFondoSanciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSanciones.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblSanciones.setText("SANCIONES");
        jPanel1.add(lblSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 280, 40));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombre.setText("ID usuario a sancionar");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 270, -1));
        jPanel1.add(txtIdUsuarioASancionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 280, 30));

        TipoSancion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TipoSancion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN" }));
        jPanel1.add(TipoSancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 260, 40));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFecha.setText("Tipo de Sancion");
        jPanel1.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 240, -1));

        lblMotivo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMotivo.setText("Motivo:");
        jPanel1.add(lblMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 140, -1));

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        jScrollPane1.setViewportView(txtMotivo);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 280, -1));

        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnviar.setText("ENVIAR");
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 130, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, 150, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 570, 130, 40));

        lblSancionImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SancionForm.png"))); // NOI18N
        jPanel1.add(lblSancionImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 310, 280));

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
    private javax.swing.JComboBox<String> TipoSancion;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFondoSanciones;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSancionImagen;
    private javax.swing.JLabel lblSanciones;
    private javax.swing.JTextField txtIdUsuarioASancionar;
    private javax.swing.JTextArea txtMotivo;
    // End of variables declaration//GEN-END:variables

}
