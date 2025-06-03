package com.mycompany.integrador4a.igu;

import Entidad.DetalleSolicitud;
import Entidad.Solicitud;
import Entidad.Usuario;
import Logica.ProgramarSolicitudPrestamo;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class VerMisPrestamos extends javax.swing.JFrame {
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    private Usuario usuario;

    public VerMisPrestamos(Usuario usuario) {
        initComponents();

        this.usuario = usuario;

        emf = Persistence.createEntityManagerFactory("PU");
        entityManager = emf.createEntityManager();

        // Listener para habilitar/deshabilitar botón según estado
        TablaMisPrestamos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = TablaMisPrestamos.getSelectedRow();
                if (fila >= 0) {
                    String estado = (String) TablaMisPrestamos.getValueAt(fila, 5);
                    CancelarPrestamo.setEnabled("PENDIENTE".equalsIgnoreCase(estado));
                } else {
                    CancelarPrestamo.setEnabled(false);
                }
            }
        });

        // Acción del botón "Cancelar"
        CancelarPrestamo.addActionListener(e -> {
            int fila = TablaMisPrestamos.getSelectedRow();
            if (fila >= 0) {
                Long idSolicitud = (Long) TablaMisPrestamos.getValueAt(fila, 0);

                ProgramarSolicitudPrestamo logica = new ProgramarSolicitudPrestamo();
                boolean exito = logica.cancelarSolicitud(idSolicitud);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Préstamo cancelado correctamente.");
                    
                    // Limpiar caché del EntityManager y refrescar la tabla
                    entityManager.clear(); 
                    refrescarTabla();
                    CancelarPrestamo.setEnabled(false);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo cancelar el préstamo.");
                }
            }
        });

        cargarTablaPrestamos(usuario);
    }

public void refrescarTabla() {
    if (entityManager.isOpen()) {
        entityManager.close();
    }
    entityManager = emf.createEntityManager(); // Nueva conexión limpia
    cargarTablaPrestamos(usuario);
}


    public void cargarTablaPrestamos(Usuario usuario) {
        String[] columnas = {
            "ID Solicitud", "Fecha Solicitud", "Fecha Uso",
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
        List<Solicitud> listaSolicitudes = listarSolicitudesPorUsuario(usuario);

        for (Solicitud s : listaSolicitudes) {
            for (DetalleSolicitud d : s.getDetalles()) {
                Object[] fila = new Object[8];
                fila[0] = s.getIdSolicitud();
                fila[1] = formato.format(s.getFechaSolicitud());
                fila[2] = formato.format(s.getFechaUso());
                fila[3] = s.getHoraInicio();
                fila[4] = s.getHoraFin();
                fila[5] = s.getEstado();
                fila[6] = d.getTipoServicio();
                fila[7] = d.getElemento();
                modelo.addRow(fila);
            }
        }

        TablaMisPrestamos.setModel(modelo);
    }

    public List<Solicitud> listarSolicitudesPorUsuario(Usuario usuario) {
        String jpql = "SELECT s FROM Solicitud s LEFT JOIN FETCH s.detalles WHERE s.usuario.id = :usuarioId";
        TypedQuery<Solicitud> query = entityManager.createQuery(jpql, Solicitud.class);
        query.setParameter("usuarioId", usuario.getId());
        return query.getResultList();
    }

    public JButton getRealizarPrestamo() {
        return RealizarPrestamo;
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
        lblSolicitarPrestamo = new javax.swing.JLabel();
        btnMenuPrincipal = new javax.swing.JButton();
        CancelarPrestamo = new javax.swing.JButton();
        RealizarPrestamo = new javax.swing.JButton();
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
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 160, 40));

        CancelarPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CancelarPrestamo.setText("Cancelar por ID");
        jPanel1.add(CancelarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, 190, 40));

        RealizarPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RealizarPrestamo.setText("REALIZAR PRESTAMO");
        jPanel1.add(RealizarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, 190, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
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

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarPrestamo;
    private javax.swing.JButton RealizarPrestamo;
    private javax.swing.JTable TablaMisPrestamos;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondoSolicitud;
    private javax.swing.JLabel lblSolicitarPrestamo;
    // End of variables declaration//GEN-END:variables

}
