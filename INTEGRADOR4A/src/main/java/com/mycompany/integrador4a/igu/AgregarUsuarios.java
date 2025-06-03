package com.mycompany.integrador4a.igu;

import Entidad.Usuario;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Logica.GestionarUsuariosLogica; 

public class AgregarUsuarios extends javax.swing.JFrame {

    private GestionarUsuarios gestionarUsuariosGUI;  // Clase GUI con la tabla
    private GestionarUsuariosLogica gestionarUsuariosLogica;  // Clase lógica para BD
    /** Creates new form AgregarUsuarios */
    public AgregarUsuarios(GestionarUsuarios gestionarUsuariosGUI) {
    initComponents();
    this.gestionarUsuariosGUI = gestionarUsuariosGUI;
    this.gestionarUsuariosLogica = new GestionarUsuariosLogica();

        btnAgregar.addActionListener(e -> {
            String nombre = getInsertarNombre().getText();
            String apellido = getInsertarApellido().getText();
            String correo = getInsertarEmail().getText();
            String clave = getInsertarContraseña().getText();
            String rol = (String) getTipoRol().getSelectedItem();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || clave.isEmpty() || rol == null) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(correo);
            usuario.setClave(clave);
            usuario.setNivel(rol);

            boolean exito = gestionarUsuariosLogica.crearUsuario(usuario);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Usuario creado correctamente.");
                // Actualizar la tabla de usuarios en la ventana gestionarUsuarios
                gestionarUsuariosGUI.cargarTablaUsuarios();
                // Opcional: limpiar campos o cerrar ventana
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear usuario.");
            }
        });
    }

    public JTextField getInsertarApellido() {
        return InsertarApellido;
    }

    public JTextField getInsertarContraseña() {
        return InsertarContraseña;
    }

    public JTextField getInsertarEmail() {
        return InsertarEmail;
    }

    public JTextField getInsertarNombre() {
        return InsertarNombre;
    }

    public JComboBox<String> getTipoRol() {
        return TipoRol;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }
    
    public JButton getBtnSalir() {
        return btnSalir;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGestionUsuarios = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        InsertarNombre = new javax.swing.JTextField();
        nombre = new javax.swing.JLabel();
        apellido = new javax.swing.JLabel();
        InsertarApellido = new javax.swing.JTextField();
        rol = new javax.swing.JLabel();
        InsertarContraseña = new javax.swing.JTextField();
        email = new javax.swing.JLabel();
        InsertarEmail = new javax.swing.JTextField();
        contraseña = new javax.swing.JLabel();
        TipoRol = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        lblFondoGestionUsuarios = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGestionUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblGestionUsuarios.setText("AGREGAR USUARIOS");
        jPanel1.add(lblGestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 560, 40));

        btnBorrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrar.setText("BORRAR");
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("REGRESAR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 580, 170, 40));
        jPanel1.add(InsertarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 160, -1));

        nombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nombre.setText("NOMBRE:");
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 90, 20));

        apellido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        apellido.setText("APELLIDO:");
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 110, 20));
        jPanel1.add(InsertarApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 160, -1));

        rol.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rol.setText("ROL:");
        jPanel1.add(rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 50, 20));
        jPanel1.add(InsertarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 460, -1));

        email.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        email.setText("EMAIL:");
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 70, 20));
        jPanel1.add(InsertarEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 460, -1));

        contraseña.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        contraseña.setText("CONTRASEÑA:");
        jPanel1.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 140, 20));

        TipoRol.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TipoRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN" }));
        jPanel1.add(TipoRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 120, 40));

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setText("AGREGAR");
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, 170, 40));

        lblFondoGestionUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoAdmin.jpg"))); // NOI18N
        jPanel1.add(lblFondoGestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

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
    private javax.swing.JTextField InsertarApellido;
    private javax.swing.JTextField InsertarContraseña;
    private javax.swing.JTextField InsertarEmail;
    private javax.swing.JTextField InsertarNombre;
    private javax.swing.JComboBox<String> TipoRol;
    private javax.swing.JLabel apellido;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel contraseña;
    private javax.swing.JLabel email;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondoGestionUsuarios;
    private javax.swing.JLabel lblGestionUsuarios;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel rol;
    // End of variables declaration//GEN-END:variables

}
