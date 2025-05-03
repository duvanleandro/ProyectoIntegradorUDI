/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import javax.swing.JComboBox;

public class HorarioAyuda {
    
    public static void actualizarComboHoraFin(String horaInicio, JComboBox<String> comboHoraFin) {
        comboHoraFin.removeAllItems();
        
        int horaInicioInt = convertirHoraAMilitar(horaInicio); 
        

        for (int i = 1; i <= 4; i++) { 
            int horaFin = horaInicioInt + i;
            if (horaFin <= 19) { 
                comboHoraFin.addItem(convertirHoraA12Horas(horaFin));
            }
        }
    }

    
    private static int convertirHoraAMilitar(String hora) {

        String[] parts = hora.split(":");
        int horaEntera = Integer.parseInt(parts[0].trim());
        String amPm = parts[1].trim().split(" ")[1];
        
        if (amPm.equalsIgnoreCase("PM") && horaEntera != 12) {
            horaEntera += 12; 
        }
        if (amPm.equalsIgnoreCase("AM") && horaEntera == 12) {
            horaEntera = 0;
        }
        
        return horaEntera;
    }

 
    private static String convertirHoraA12Horas(int hora) {
        String amPm = (hora < 12) ? "AM" : "PM";
        if (hora == 0) {
            return "12:00 AM";
        }
        if (hora == 12) {
            return "12:00 PM"; 
        }
        if (hora > 12) {
            hora -= 12; 
        }
        return String.format("%d:00 %s", hora, amPm);
    }
}

