package Interfaz;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;

public class ActualizarFecha {
     public static void llenarFechas(JComboBox<String> comboFecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoy = LocalDate.now();

        comboFecha.removeAllItems();
        for (int i = 0; i < 7; i++) {
            LocalDate fecha = hoy.plusDays(i);
            DayOfWeek diaSemana = fecha.getDayOfWeek();
            String nombreDia = diaSemana.toString();
            String nombreDiaEspañol = traducirDia(nombreDia); 
            
            String fechaConDia = fecha.format(formatter) + " - " + nombreDiaEspañol;
            comboFecha.addItem(fechaConDia); 
        }
    }

    private static String traducirDia(String dia) {
        switch (dia) {
            case "MONDAY":
                return "Lunes";
            case "TUESDAY":
                return "Martes";
            case "WEDNESDAY":
                return "Miércoles";
            case "THURSDAY":
                return "Jueves";
            case "FRIDAY":
                return "Viernes";
            case "SATURDAY":
                return "Sábado";
            case "SUNDAY":
                return "Domingo";
            default:
                return "";
        }
    }
}