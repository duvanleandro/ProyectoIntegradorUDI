package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {

    private Connection cn;

    public Connection conectar() {
        try {
            if (cn == null || cn.isClosed()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                cn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "dsmProyecto",
                        "dsmProyecto"
                );
                System.out.println("Se ha establecido la conexión con la base de datos");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver de Oracle: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
        return cn;
    }

    public void cerrarConexion() {
        try {
            if (cn != null && !cn.isClosed()) {
                cn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
