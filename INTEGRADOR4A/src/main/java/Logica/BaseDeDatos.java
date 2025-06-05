package Logica;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class BaseDeDatos {

   public static void reiniciarBaseDeDatos(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();

    // Deshabilitar constraints FK que puedan bloquear los deletes
    stmt.executeUpdate("ALTER TABLE SOPORTE DISABLE CONSTRAINT FK_SOPORTE_USUARIO");
    stmt.executeUpdate("ALTER TABLE SOLICITUDES DISABLE CONSTRAINT FK_SOLICITUDES_USUARIO");
    stmt.executeUpdate("ALTER TABLE SANCIONES DISABLE CONSTRAINT FK_SANCION_USUARIO");
    stmt.executeUpdate("ALTER TABLE ENCUESTAS DISABLE CONSTRAINT FK_ENCUESTAS_USUARIO"); // nuevo

    try {
        // Borrar datos en orden correcto para no violar FK
        stmt.executeUpdate("DELETE FROM ENCUESTAS"); // nuevo
        stmt.executeUpdate("DELETE FROM PRESTAMOS");
        stmt.executeUpdate("DELETE FROM DETALLE_SOLICITUD");
        stmt.executeUpdate("DELETE FROM SOLICITUDES");
        stmt.executeUpdate("DELETE FROM EQUIPOS");
        stmt.executeUpdate("DELETE FROM SALAS");
        stmt.executeUpdate("DELETE FROM SOPORTE");
        stmt.executeUpdate("DELETE FROM SANCIONES");
        stmt.executeUpdate("DELETE FROM USUARIOS");

        // Eliminar y recrear secuencias
        stmt.executeUpdate("DROP SEQUENCE SEQ_ENCUESTAS"); // nuevo
        stmt.executeUpdate("CREATE SEQUENCE SEQ_ENCUESTAS START WITH 1 INCREMENT BY 1"); // nuevo

        stmt.executeUpdate("DROP SEQUENCE SEQ_USUARIOS_ID");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_USUARIOS_ID START WITH 4 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_PRESTAMOS_ID");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_PRESTAMOS_ID START WITH 1 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_EQUIPOS");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_EQUIPOS START WITH 1 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_SALAS");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_SALAS START WITH 1 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_SOLICITUDES_ID");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_SOLICITUDES_ID START WITH 1 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_SOLICITUDES");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_SOLICITUDES START WITH 1 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_DETALLE_SOLICITUD");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_DETALLE_SOLICITUD START WITH 1 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_SOPORTE");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_SOPORTE START WITH 1 INCREMENT BY 1");

        stmt.executeUpdate("DROP SEQUENCE SEQ_SANCIONES");
        stmt.executeUpdate("CREATE SEQUENCE SEQ_SANCIONES START WITH 1 INCREMENT BY 1");

        // Insertar admin (ID = 1)
        stmt.executeUpdate("INSERT INTO USUARIOS (ID, NOMBRE, APELLIDO, EMAIL, CLAVE, NIVEL) " +
                "VALUES (1, 'admin', 'admin', 'a', 'a', 'ADMIN')");

        // Insertar usuario (ID = 2)
        stmt.executeUpdate("INSERT INTO USUARIOS (ID, NOMBRE, APELLIDO, EMAIL, CLAVE, NIVEL) " +
                "VALUES (2, 'usuario', 'usuario', 'u', 'u', 'USER')");

        // Usuario extra (ID = 3)
        stmt.executeUpdate("INSERT INTO USUARIOS (ID, NOMBRE, APELLIDO, EMAIL, CLAVE, NIVEL) " +
                "VALUES (3, 'e', 'e', 'e', 'e', 'USER')");

        // Insertar salas base
        stmt.executeUpdate("INSERT INTO SALAS (ID, NOMBRE) VALUES (1, 'Sala A - Programacion')");
        stmt.executeUpdate("INSERT INTO SALAS (ID, NOMBRE) VALUES (2, 'Sala B - Diseño grafico')");
        stmt.executeUpdate("INSERT INTO SALAS (ID, NOMBRE) VALUES (3, 'Sala C - Diseño De Video')");

        // Insertar equipos base
        stmt.executeUpdate("INSERT INTO EQUIPOS (ID, NOMBRE) VALUES (1, 'Camara')");
        stmt.executeUpdate("INSERT INTO EQUIPOS (ID, NOMBRE) VALUES (2, 'Televisor')");
        stmt.executeUpdate("INSERT INTO EQUIPOS (ID, NOMBRE) VALUES (3, 'Proyector')");

        // Insertar encuesta de prueba (opcional)
        stmt.executeUpdate("INSERT INTO ENCUESTAS (ID_USUARIO, MENSAJE) VALUES (2, 'Me gustó el servicio')");

    } finally {
        // Habilitar constraints FK nuevamente
        stmt.executeUpdate("ALTER TABLE SOPORTE ENABLE CONSTRAINT FK_SOPORTE_USUARIO");
        stmt.executeUpdate("ALTER TABLE SOLICITUDES ENABLE CONSTRAINT FK_SOLICITUDES_USUARIO");
        stmt.executeUpdate("ALTER TABLE SANCIONES ENABLE CONSTRAINT FK_SANCION_USUARIO");
        stmt.executeUpdate("ALTER TABLE ENCUESTAS ENABLE CONSTRAINT FK_ENCUESTAS_USUARIO"); // nuevo
        stmt.close();
    }
}
}