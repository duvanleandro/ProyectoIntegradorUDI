/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class PlaceHolder {
 public static void aplicarPlaceholder(JTextField campo, String textoPlaceholder) {
        campo.setText(textoPlaceholder);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(textoPlaceholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(textoPlaceholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }
}
