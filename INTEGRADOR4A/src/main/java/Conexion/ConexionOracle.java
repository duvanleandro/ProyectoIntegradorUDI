package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {
    Connection cn;
    
    public Connection conectar(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "dsmProyecto",
                "dsmProyecto"
            );
            System.out.println("Se ha establecido la conexión con la base de datos");
        } catch (Exception e) {
            System.out.println("Error de conexión DB: " + e);
        }
        return cn;
    }
}
