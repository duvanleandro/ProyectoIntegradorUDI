package com.mycompany.integrador4a.igu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import Entidad.Solicitud;
import Entidad.DetalleSolicitud;
import Entidad.Usuario;
import Logica.RealizarSolicitudP;
import javax.swing.JButton;

public class GestionarSolicitudes extends javax.swing.JFrame {
    
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public GestionarSolicitudes(Usuario usuario) {
        initComponents();
        emf = Persistence.createEntityManagerFactory("PU");
        entityManager = emf.createEntityManager();
        
        cargarTablaSolicitudes();  // carga todas las solicitudes al iniciar
        
        TablaSolicitudes.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        actualizarEstadoBotones();
    }
});
        btnAceptar.addActionListener(e -> {
    int fila = TablaSolicitudes.getSelectedRow();
    if (fila != -1) {
        Long idSolicitud = Long.parseLong(TablaSolicitudes.getModel().getValueAt(fila, 0).toString());

        // Llama a tu lógica para aceptar la solicitud
        RealizarSolicitudP logica = new RealizarSolicitudP();
        boolean exito = logica.aceptarSolicitud(idSolicitud);
        logica.cerrar();

        if (exito) {
            cargarTablaSolicitudes();
            actualizarEstadoBotones();
        } else {
            // Mostrar mensaje de error si quieres
        }
    }
});

btnRechazar.addActionListener(e -> {
    int fila = TablaSolicitudes.getSelectedRow();
    if (fila != -1) {
        Long idSolicitud = Long.parseLong(TablaSolicitudes.getModel().getValueAt(fila, 0).toString());

        // Llama a tu lógica para rechazar la solicitud
        RealizarSolicitudP logica = new RealizarSolicitudP();
        boolean exito = logica.rechazarSolicitud(idSolicitud);
        logica.cerrar();

        if (exito) {
            cargarTablaSolicitudes();
            actualizarEstadoBotones();
        } else {
            // Mostrar mensaje de error si quieres
        }
    }
});


    }
    
    private void actualizarEstadoBotones() {
    int fila = TablaSolicitudes.getSelectedRow();
    if (fila == -1) {
        // No hay fila seleccionada: deshabilitar botones
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
        return;
    }

    // El modelo que usas es DefaultTableModel, la columna estado es la 6
    String estado = TablaSolicitudes.getModel().getValueAt(fila, 6).toString();

    if ("PENDIENTE".equalsIgnoreCase(estado)) {
        btnAceptar.setEnabled(true);
        btnRechazar.setEnabled(true);
    } else {
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
    }
}


public void cargarTablaSolicitudes() {
    String[] columnas = {
        "ID Solicitud", "Usuario", "Fecha Solicitud", "Fecha Uso",
        "Hora Inicio", "Hora Fin", "Estado",
        "Tipo Servicio", "Elemento"
    };

    DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - EEEE", new Locale("es", "ES"));

    // 👉 Limpiar la caché antes de volver a consultar (clave para ver los cambios en tiempo real)
    entityManager.clear();  
    entityManager.getEntityManagerFactory().getCache().evictAll();

    // Consulta para traer todas las solicitudes con sus detalles y usuario
    String jpql = "SELECT DISTINCT s FROM Solicitud s LEFT JOIN FETCH s.detalles LEFT JOIN FETCH s.usuario";
    TypedQuery<Solicitud> query = entityManager.createQuery(jpql, Solicitud.class);
    List<Solicitud> listaSolicitudes = query.getResultList();

    for (Solicitud s : listaSolicitudes) {
        for (DetalleSolicitud d : s.getDetalles()) {
            Object[] fila = new Object[9];
            fila[0] = s.getIdSolicitud();
            fila[1] = s.getUsuario().getNombre(); // Asumiendo getNombre() en Usuario
            fila[2] = formato.format(s.getFechaSolicitud());
            fila[3] = formato.format(s.getFechaUso());
            fila[4] = s.getHoraInicio();
            fila[5] = s.getHoraFin();
            fila[6] = s.getEstado();
            fila[7] = d.getTipoServicio();
            fila[8] = d.getElemento();
            modelo.addRow(fila);
        }
    }

    TablaSolicitudes.setModel(modelo);
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
        lblGestionSolicitud = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSolicitudes = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnRechazar = new javax.swing.JButton();
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

        btnAceptar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAceptar.setText("ACEPTAR");
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 170, 40));

        btnRechazar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRechazar.setText("RECHAZAR");
        jPanel1.add(btnRechazar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, 170, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 170, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaSolicitudes;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnRechazar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoGestionSolicitud;
    private javax.swing.JLabel lblGestionSolicitud;
    // End of variables declaration//GEN-END:variables

}
