
package Logica;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class OtrosFuncionamientos {
         public static void pintar(JLabel lbl, String ruta) {
        ImageIcon imagen = new ImageIcon(OtrosFuncionamientos.class.getResource(ruta));
        ImageIcon icono = new ImageIcon(
            imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH)
        );
        lbl.setIcon(icono);
    }
}
