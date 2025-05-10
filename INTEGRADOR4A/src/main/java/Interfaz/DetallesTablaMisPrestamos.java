/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DetallesTablaMisPrestamos extends JDialog {

    public DetallesTablaMisPrestamos(Frame parent) {
        super(parent, "Detalles del préstamo", true);
        setLayout(new BorderLayout());

        JPanel botonesPanel = new JPanel();
        JButton btnVerElementos = new JButton("Ver elementos solicitados");
        JButton btnVerComentarios = new JButton("Ver comentarios del administrador");

        botonesPanel.add(btnVerElementos);
        botonesPanel.add(btnVerComentarios);

        JTextArea areaComentarios = new JTextArea(5, 30);
        areaComentarios.setLineWrap(true);
        areaComentarios.setWrapStyleWord(true);
        areaComentarios.setEditable(false);
        areaComentarios.setText("Comentario del administrador: Aprobado con observaciones...");

        JTable tablaElementos = new JTable(new DefaultTableModel(new Object[]{"Tipo servicio", "Elemento reserva"}, 0));
        JScrollPane scrollTabla = new JScrollPane(tablaElementos);

        // Datos de prueba — en tu caso deberías obtener esto desde la base de datos
        DefaultTableModel modelo = (DefaultTableModel) tablaElementos.getModel();
        modelo.addRow(new Object[]{"Sala de Informática", "Sala A - Diseño"});
        modelo.addRow(new Object[]{"Equipo Audiovisual", "Micrófono"});
        modelo.addRow(new Object[]{"Equipo Audiovisual", "Proyector"});

        JPanel contenidoPanel = new JPanel(new CardLayout());
        contenidoPanel.add(scrollTabla, "tabla");
        contenidoPanel.add(new JScrollPane(areaComentarios), "comentarios");

        CardLayout layout = (CardLayout) contenidoPanel.getLayout();

        btnVerElementos.addActionListener(e -> layout.show(contenidoPanel, "tabla"));
        btnVerComentarios.addActionListener(e -> layout.show(contenidoPanel, "comentarios"));

        add(botonesPanel, BorderLayout.NORTH);
        add(contenidoPanel, BorderLayout.CENTER);

        setSize(500, 300);
        setLocationRelativeTo(parent);
    }
}
