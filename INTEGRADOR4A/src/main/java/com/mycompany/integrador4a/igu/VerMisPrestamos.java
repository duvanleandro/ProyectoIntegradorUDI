package com.mycompany.integrador4a.igu;

import Entidad.DetalleSolicitud;
import Entidad.Solicitud;
import Entidad.Usuario;
import Logica.RealizarSolicitud;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerMisPrestamos extends javax.swing.JFrame {

    private EntityManager entityManager;
    private EntityManagerFactory emf;
    private Usuario usuario;

    public VerMisPrestamos(Usuario usuario) {
        initComponents();

        this.usuario = usuario;

        emf = Persistence.createEntityManagerFactory("PU");
        entityManager = emf.createEntityManager();

        // Listener para habilitar/deshabilitar botón Cancelar según estado
        TablaMisPrestamos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int fila = TablaMisPrestamos.getSelectedRow();
                    if (fila >= 0) {
                        String estado = (String) TablaMisPrestamos.getValueAt(fila, 5);
                        CancelarPrestamo.setEnabled("PENDIENTE".equalsIgnoreCase(estado));
                    } else {
                        CancelarPrestamo.setEnabled(false);
                    }
                }
            }
        });

        // Acción del botón "Cancelar"
        CancelarPrestamo.addActionListener(e -> {
            int fila = TablaMisPrestamos.getSelectedRow();
            if (fila >= 0) {
                Long idSolicitud = (Long) TablaMisPrestamos.getValueAt(fila, 0);

                RealizarSolicitud logica = new RealizarSolicitud();
                boolean exito = logica.cancelarSolicitud(idSolicitud);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Préstamo cancelado correctamente.");

                    // Limpiar caché y refrescar tabla
                    entityManager.clear();
                    refrescarTabla();
                    CancelarPrestamo.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo cancelar el préstamo.");
                }
            }
        });

        cargarTablaPrestamos(usuario);

        // Configurar renderer y editor para la columna botón "VER"
        configurarColumnaBoton();
    }

    public void refrescarTabla() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = emf.createEntityManager(); // Nueva conexión limpia
        cargarTablaPrestamos(usuario);
        configurarColumnaBoton();  // Reconfigurar botones al recargar
    }

    public void cargarTablaPrestamos(Usuario usuario) {
        String[] columnas = {
            "ID Solicitud", "Fecha Solicitud", "Fecha Uso",
            "Hora Inicio", "Hora Fin", "Estado",
            "Elementos"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo columna "Elementos" (última) editable para el botón
                return column == 6;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) {
                    return JButton.class; // Para que JTable sepa que es botón
                }
                return String.class;
            }
        };

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - EEEE", new Locale("es", "ES"));
        List<Solicitud> listaSolicitudes = listarSolicitudesPorUsuario(usuario);

        for (Solicitud s : listaSolicitudes) {
            // En la columna elementos ponemos un botón "VER"
            JButton btnVer = new JButton("VER");

            Object[] fila = new Object[7];
            fila[0] = s.getIdSolicitud();
            fila[1] = formato.format(s.getFechaSolicitud());
            fila[2] = formato.format(s.getFechaUso());
            fila[3] = s.getHoraInicio();
            fila[4] = s.getHoraFin();
            fila[5] = s.getEstado();
            fila[6] = btnVer;

            modelo.addRow(fila);
        }

        TablaMisPrestamos.setModel(modelo);
    }

    public List<Solicitud> listarSolicitudesPorUsuario(Usuario usuario) {
        String jpql = "SELECT DISTINCT s FROM Solicitud s LEFT JOIN FETCH s.detalles WHERE s.usuario.id = :usuarioId";
        TypedQuery<Solicitud> query = entityManager.createQuery(jpql, Solicitud.class);
        query.setParameter("usuarioId", usuario.getId());
        return query.getResultList();
    }

    private void configurarColumnaBoton() {
        // Renderer para botón en columna "Elementos"
        TablaMisPrestamos.getColumn("Elementos").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof JButton) {
                    return (JButton) value;
                }
                return new JLabel(value != null ? value.toString() : "");
            }
        });

        // Editor para botón, para capturar clic
        TablaMisPrestamos.getColumn("Elementos").setCellEditor(new BotonEditor(new JButton("VER")));
    }

    // Editor personalizado para el botón en JTable
    private class BotonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JButton boton;
        private Long idSolicitudSeleccionada;

        public BotonEditor(JButton boton) {
            this.boton = boton;
            this.boton.addActionListener(this);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            // Obtener ID de la solicitud de la fila clickeada
            Object idObj = table.getValueAt(row, 0);
            if (idObj instanceof Long) {
                idSolicitudSeleccionada = (Long) idObj;
            } else if (idObj instanceof Integer) {
                // Por si viene como Integer
                idSolicitudSeleccionada = ((Integer) idObj).longValue();
            } else {
                idSolicitudSeleccionada = null;
            }
            return boton;
        }

        @Override
        public Object getCellEditorValue() {
            return boton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Cuando se hace clic en el botón "VER"
            if (idSolicitudSeleccionada != null) {
                SwingUtilities.invokeLater(() -> abrirVentanaDetalles(idSolicitudSeleccionada));
            }
            // Termina la edición para que el botón se "libere"
            fireEditingStopped();
        }
    }

    private void abrirVentanaDetalles(Long idSolicitud) {
        DetallePrestamoDialog dialog = new DetallePrestamoDialog(this, true, idSolicitud);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Clase interna para el diálogo que muestra detalles de la solicitud
    private class DetallePrestamoDialog extends JDialog {
        private JTable tablaDetalles;
        private DefaultTableModel modelo;

        public DetallePrestamoDialog(java.awt.Frame parent, boolean modal, Long idSolicitud) {
            super(parent, modal);
            setTitle("Detalles del préstamo - ID: " + idSolicitud);
            setSize(400, 300);

            String[] columnas = {"Tipo Servicio", "Elemento"};
            modelo = new DefaultTableModel(null, columnas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaDetalles = new JTable(modelo);
            add(new JScrollPane(tablaDetalles));

            cargarDetalles(idSolicitud);
        }

        private void cargarDetalles(Long idSolicitud) {
            List<DetalleSolicitud> detalles = obtenerDetallesPorSolicitud(idSolicitud);

            for (DetalleSolicitud d : detalles) {
                Object[] fila = new Object[2];
                fila[0] = d.getTipoServicio();
                fila[1] = d.getElemento();
                modelo.addRow(fila);
            }
        }

        private List<DetalleSolicitud> obtenerDetallesPorSolicitud(Long idSolicitud) {
            EntityManager em = emf.createEntityManager();
            List<DetalleSolicitud> detalles = null;
            try {
                String jpql = "SELECT d FROM DetalleSolicitud d WHERE d.solicitud.idSolicitud = :id";
                detalles = em.createQuery(jpql, DetalleSolicitud.class)
                        .setParameter("id", idSolicitud)
                        .getResultList();
            } finally {
                em.close();
            }
            return detalles;
        }
    }

    // Métodos get para botones (según tu código original)
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
