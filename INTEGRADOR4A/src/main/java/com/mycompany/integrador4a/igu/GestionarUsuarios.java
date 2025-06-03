package com.mycompany.integrador4a.igu;

import Entidad.Usuario;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GestionarUsuarios extends javax.swing.JFrame {

    private Logica.GestionarUsuarios gestionarUsuariosLogica;

    public GestionarUsuarios() {
        initComponents();
        gestionarUsuariosLogica = new Logica.GestionarUsuarios();
        cargarTablaUsuarios();

        // Agregar listener al botón modificar
        btnModificar.addActionListener(e -> abrirVentanaEditar());
        btnEliminar.addActionListener(e -> eliminarUsuario());

    }
    
    private void abrirVentanaEditar() {
        int filaSeleccionada = TablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario para editar.");
            return;
        }

        Long id = (Long) TablaUsuarios.getValueAt(filaSeleccionada, 0);
        String nombre = (String) TablaUsuarios.getValueAt(filaSeleccionada, 1);
        String apellido = (String) TablaUsuarios.getValueAt(filaSeleccionada, 2);
        String correo = (String) TablaUsuarios.getValueAt(filaSeleccionada, 3);
        String claveActual = (String) TablaUsuarios.getValueAt(filaSeleccionada, 4);
        String nivel = (String) TablaUsuarios.getValueAt(filaSeleccionada, 5);

        JDialog dialog = new JDialog(this, "Editar Usuario", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(7, 2, 5, 5));
        dialog.setLocationRelativeTo(this);

        dialog.add(new JLabel("ID:"));
        dialog.add(new JLabel(String.valueOf(id)));

        JTextField txtNombre = new JTextField(nombre);
        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);

        JTextField txtApellido = new JTextField(apellido);
        dialog.add(new JLabel("Apellido:"));
        dialog.add(txtApellido);

        JTextField txtCorreo = new JTextField(correo);
        txtCorreo.setEditable(false);  // No editable
        dialog.add(new JLabel("Correo:"));
        dialog.add(txtCorreo);

        JPasswordField txtClave = new JPasswordField();
        txtClave.setToolTipText("Deja vacío para no cambiar la clave");
        dialog.add(new JLabel("Clave:"));
        dialog.add(txtClave);

        JComboBox<String> cmbNivel = new JComboBox<>(new String[]{"ADMIN", "USER"});
        cmbNivel.setSelectedItem(nivel);
        dialog.add(new JLabel("Nivel:"));
        dialog.add(cmbNivel);

        JButton btnActualizar = new JButton("Actualizar");
        JButton btnCancelar = new JButton("Cancelar");
        dialog.add(btnActualizar);
        dialog.add(btnCancelar);

        btnActualizar.addActionListener(e -> {
            String nuevaClave = new String(txtClave.getPassword());

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNombre(txtNombre.getText());
            usuario.setApellido(txtApellido.getText());
            usuario.setEmail(correo); // siempre el correo original
            if (nuevaClave.isEmpty()) {
                usuario.setClave(claveActual); // no cambia la clave
            } else {
                usuario.setClave(nuevaClave);
            }
            usuario.setNivel((String) cmbNivel.getSelectedItem());

            if (gestionarUsuariosLogica.modificarUsuario(usuario)) {
                JOptionPane.showMessageDialog(dialog, "Usuario actualizado correctamente.");
                dialog.dispose();
                cargarTablaUsuarios();
            } else {
                JOptionPane.showMessageDialog(dialog, "Error al actualizar usuario.");
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void eliminarUsuario() {
    int filaSeleccionada = TablaUsuarios.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario para eliminar.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    Long id = (Long) TablaUsuarios.getValueAt(filaSeleccionada, 0);

    if (gestionarUsuariosLogica.borrarUsuarioPorId(id)) {
        JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
        cargarTablaUsuarios();
    } else {
        JOptionPane.showMessageDialog(this, "Error al eliminar usuario.");
    }
}

    
    public void cargarTablaUsuarios() {
        String[] columnas = {"ID", "Nombre", "Apellido", "Correo", "Clave", "Nivel"};

        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };

        List<Usuario> listaUsuarios = gestionarUsuariosLogica.listarUsuarios();

        for (Usuario u : listaUsuarios) {
            Object[] fila = new Object[6];
            fila[0] = u.getId();
            fila[1] = u.getNombre();
            fila[2] = u.getApellido();
            fila[3] = u.getEmail();
            fila[4] = u.getClave();
            fila[5] = u.getNivel();
            modelo.addRow(fila);
        }

        TablaUsuarios.setModel(modelo);
    }

    // Getters para componentes si los necesitas
    public javax.swing.JTable getTablaUsuarios() {
        return TablaUsuarios;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnMenuPrincipal() {
        return btnMenuPrincipal;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGestionUsuarios = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        lblFondoGestionUsuarios = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGestionUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblGestionUsuarios.setText("GESTION DE USUARIOS");
        jPanel1.add(lblGestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 560, 40));

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setText("AGREGAR");
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 170, 40));

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModificar.setText("MODIFICAR");
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 580, 170, 40));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 580, 170, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 580, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, 170, 40));

        TablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(TablaUsuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 990, -1));

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
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoGestionUsuarios;
    private javax.swing.JLabel lblGestionUsuarios;
    // End of variables declaration//GEN-END:variables

}
