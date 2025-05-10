import java.awt.Component;
import java.awt.Color;
import java.util.Set;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;

public class ColorDisponibilidad {

    public static void aplicarColores(JComboBox<String> comboBox, Set<String> horasOcupadas) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                String hora = value.toString();

                if (horasOcupadas.contains(hora)) {
                    c.setForeground(Color.GRAY);
                } else {
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });
    }
}
