package com.mycompany.integrador4a.igu;

import Entidad.Equipos;
import Entidad.Salas;
import Entidad.Usuario;
import Logica.RealizarSolicitud;

import java.time.LocalDate;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Ventana para que el usuario seleccione equipos o salas, agregue a la tabla resumen
 * y finalmente “Enviar” la solicitud.
 */
public class PedirPrestamo extends javax.swing.JFrame {

    private RealizarSolicitud programarSolicitudPrestamo;
    private Usuario usuarioLogueado;
    private VerMisPrestamos verMisPrestamosInstance;  // referencia para refrescar tabla

    public PedirPrestamo(Usuario usuario, VerMisPrestamos verMisPrestamos) {
        initComponents();

        this.usuarioLogueado = usuario;
        this.verMisPrestamosInstance = verMisPrestamos;  // guardamos referencia

        programarSolicitudPrestamo = new RealizarSolicitud();

        // Que al inicio TextOtroMaterial esté deshabilitado
        TextOtroMaterial.setEnabled(false);

        // Agrupar los dos JRadioButton para que solo uno se pueda seleccionar
        ButtonGroup grupoServicio = new ButtonGroup();
        grupoServicio.add(BEquipoAudiovisual);
        grupoServicio.add(BSalaDeInformatica);

        // Listener para radio “Equipo Audiovisual”
        BEquipoAudiovisual.addActionListener(e -> {
            ElegirEquipo.removeAllItems();
            List<Equipos> lista = programarSolicitudPrestamo.listarEquipos();

            if (lista.isEmpty()) {
                // Si no hay equipos en BD, mostrar tres ejemplos mínimos
                //ElegirEquipo.addItem("Cámara");
                //ElegirEquipo.addItem("Pantalla");
                //ElegirEquipo.addItem("Micrófono");
            } else {
                for (Equipos eq : lista) {
                    ElegirEquipo.addItem(eq.getNombre());
                }
            }
            // Agregar “Otro” siempre
            ElegirEquipo.addItem("Otro");
        });

        // Listener para radio “Sala de Informática”
        BSalaDeInformatica.addActionListener(e -> {
            ElegirEquipo.removeAllItems();
            List<Salas> lista = programarSolicitudPrestamo.listarSalas();

            if (lista.isEmpty()) {
                // Si no hay salas en BD, mostrar tres ejemplos mínimos
                //ElegirEquipo.addItem("Sala A - Programación");
                //ElegirEquipo.addItem("Sala B - Edición");
                //ElegirEquipo.addItem("Sala C - Diseño");
            } else {
                for (Salas s : lista) {
                    ElegirEquipo.addItem(s.getNombre());
                }
            }
            // Agregar “Otro” siempre
            ElegirEquipo.addItem("Otro");
        });

        // Habilitar/Deshabilitar campo “Otro” si se elige “Otro”
        ElegirEquipo.addActionListener(e -> {
            if ("Otro".equals(ElegirEquipo.getSelectedItem())) {
                TextOtroMaterial.setEnabled(true);
            } else {
                TextOtroMaterial.setEnabled(false);
                TextOtroMaterial.setText("");
            }
        });

        // Botón “Agregar” → añade el detalle a la lista temporal y refresca tabla
BAgregar.addActionListener(e -> {
    String tipoServicio = BEquipoAudiovisual.isSelected()
                        ? "Equipo Audiovisual"
                        : "Sala de Informática";

    String elementoSeleccionado = (String) ElegirEquipo.getSelectedItem();
    if ("Otro".equals(elementoSeleccionado)) {
        elementoSeleccionado = TextOtroMaterial.getText().trim();
        if (elementoSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre del recurso ‘Otro’");
            return;
        }
    }

    // Validación: solo se puede agregar UNA sala
    if (tipoServicio.equals("Sala de Informática")) {
        for (RealizarSolicitud.DetalleTemp dt : programarSolicitudPrestamo.getListaDetallesTemp()) {
            if (dt.getTipoServicio().equals("Sala de Informática")) {
                JOptionPane.showMessageDialog(this, "Solo puedes solicitar una sala por vez.");
                return;
            }
        }
    }

    programarSolicitudPrestamo.agregarDetalleTemporal(tipoServicio, elementoSeleccionado);
    refrescarTablaVisual();
});

        // Llenar ElegirFecha con la fecha de hoy + los 7 días siguientes,
        // usando formato "dd/MM/yyyy - NombreDelDía" (en español)
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale localeEs = new Locale("es", "ES");

        for (int i = 0; i <= 6; i++) {
            LocalDate fecha = hoy.plusDays(i);

            // Ejemplo:  "05/06/2025"
            String fechaFormateada = fecha.format(formatter);

            // Obtener nombre del día en español, e.g. "jueves"
            DayOfWeek diaSemana = fecha.getDayOfWeek();
            String nombreDia = diaSemana.getDisplayName(TextStyle.FULL, localeEs);

            // Combinar: "05/06/2025 - jueves"
            ElegirFecha.addItem(fechaFormateada + " - " + nombreDia);
        }
        
        HoraInicio.removeAllItems();
    for (int h = 8; h <= 18; h++) {
        // Si h < 12, es "am"; si h >= 12, es "pm"
        String sufijo = (h < 12) ? "am" : "pm";
        // Convertimos a formato 12 horas (1–12)
        int hora12 = (h == 12 ? 12 : h % 12);
        HoraInicio.addItem(hora12 + ":00 " + sufijo);
    }

        // 2) Listener para poblar HoraFinal cuando cambie HoraInicio:
    HoraInicio.addActionListener(e -> {
        HoraFinal.removeAllItems();
        String inicio = (String) HoraInicio.getSelectedItem(); // ej. "8:00 am" o "12:00 pm"

        // Extraemos solo el número de hora (antes de los dos puntos)
        String parteHora = inicio.split(":")[0]; // "8" o "12"
        int h = Integer.parseInt(parteHora);

        // Si está en pm y es menor que 12, lo sumamos a 12 para obtener 24h
        if (inicio.endsWith("pm") && h < 12) {
            h += 12;
        }
        // Si es "12:00 am", h quedaría en 12 y debemos convertirlo a 0; pero
        // aquí nunca aparecerá "12:00 am" (nuestra lista empieza en 8 am).

        // Añadimos hasta 4 horas siguientes, sin pasar de 18 (6 pm)
        for (int delta = 1; delta <= 4; delta++) {
            int hFinal = h + delta;
            if (hFinal > 19) break;

            String sufFinal = (hFinal < 12) ? "am" : "pm";
            int hora12Final = (hFinal == 12 ? 12 : hFinal % 12);
            HoraFinal.addItem(hora12Final + ":00 " + sufFinal);
        }
    });

        // Agregar MouseListener para “Eliminar” en la tabla
        TablaVisual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = TablaVisual.rowAtPoint(e.getPoint());
                int columna = TablaVisual.columnAtPoint(e.getPoint());
                // Columna 2 es “Eliminar”
                if (columna == 2 && fila >= 0) {
                    // Borrar el detalle temporal correspondiente
                    programarSolicitudPrestamo.eliminarDetalleTemporal(fila);
                    refrescarTablaVisual();
                }
            }
        });

        // Botón “Enviar” → guarda en la BD la solicitud + detalles
 btnEnviar.addActionListener(e -> {
            try {
                Long idUsuario = this.usuarioLogueado.getId();
                java.sql.Date fechaSolicitud = new java.sql.Date(System.currentTimeMillis());

                String textoCompleto = (String) ElegirFecha.getSelectedItem();
                if (textoCompleto == null) {
                    JOptionPane.showMessageDialog(this, "Debes elegir una fecha de uso.");
                    return;
                }
                String fechaParte = textoCompleto.split(" - ")[0];
                java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate ld = java.time.LocalDate.parse(fechaParte, fmt);
                java.sql.Date fechaUso = java.sql.Date.valueOf(ld);

                String horaInicio = (String) HoraInicio.getSelectedItem();
                String horaFin = (String) HoraFinal.getSelectedItem();
                if (horaInicio == null || horaFin == null) {
                    JOptionPane.showMessageDialog(this, "Debes elegir hora de inicio y hora final.");
                    return;
                }

                String estado = "PENDIENTE";

                boolean exito = programarSolicitudPrestamo.registrarSolicitudJPA(
                    idUsuario, fechaSolicitud, fechaUso, horaInicio, horaFin, estado
                );

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Solicitud registrada correctamente.");
                    programarSolicitudPrestamo.getListaDetallesTemp().clear();
                    refrescarTablaVisual();
                    ElegirFecha.setSelectedIndex(0);
                    HoraInicio.setSelectedIndex(0);
                    HoraFinal.setSelectedIndex(0);

                    // Aquí llamamos a refrescar tabla en VerMisPrestamos
                    if (verMisPrestamosInstance != null) {
                        verMisPrestamosInstance.refrescarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar la solicitud.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ocurrió un error interno.");
            }
        });
    

    }

    // Reconstruye el contenido de TablaVisual a partir de la lista temporal
    private void refrescarTablaVisual() {
        List<RealizarSolicitud.DetalleTemp> detalles =
            programarSolicitudPrestamo.getListaDetallesTemp();

        String[] columnas = {"Tipo de servicio", "Elemento", "Eliminar"};
        DefaultTableModel model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (RealizarSolicitud.DetalleTemp dt : detalles) {
            Object[] fila = {
                dt.getTipoServicio(),
                dt.getElemento(),
                "X"  // Marcador para “Eliminar”
            };
            model.addRow(fila);
        }
        TablaVisual.setModel(model);
    }

    // Getters para botones de navegación (menu principal, salir, etc.)
    public JButton getBtnMenuPrincipal() {
        return btnMenuPrincipal;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnIrAudiovisual() {
        return btnIrAudiovisual;
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
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 130, 40));

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMenuPrincipal.setText("MENU PRINCIPAL");
        jPanel1.add(btnMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 580, -1, 40));

        btnIrAudiovisual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIrAudiovisual.setText("VER MIS PRESTAMOS");
        jPanel1.add(btnIrAudiovisual, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 580, 200, 40));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("SALIR");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 580, 130, 40));

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
