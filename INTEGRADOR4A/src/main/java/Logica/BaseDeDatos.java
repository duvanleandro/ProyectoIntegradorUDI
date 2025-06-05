package Logica;

import Conexion.ConexionOracle;
import Entidad.Equipos;
import Entidad.Salas;
import Entidad.Soporte;
import Entidad.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import oracle.jdbc.OracleTypes;

public class BaseDeDatos {

    public static void reiniciarBaseDeDatos(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        // Deshabilitar constraints FK que puedan bloquear los deletes
        stmt.executeUpdate("ALTER TABLE SOPORTE DISABLE CONSTRAINT FK_SOPORTE_USUARIO");
        stmt.executeUpdate("ALTER TABLE SOLICITUDES DISABLE CONSTRAINT FK_SOLICITUDES_USUARIO");
        stmt.executeUpdate("ALTER TABLE SANCIONES DISABLE CONSTRAINT FK_SANCION_USUARIO");

        try {
            // Borrar datos en orden correcto para no violar FK
            stmt.executeUpdate("DELETE FROM PRESTAMOS");
            stmt.executeUpdate("DELETE FROM DETALLE_SOLICITUD");
            stmt.executeUpdate("DELETE FROM SOLICITUDES");
            stmt.executeUpdate("DELETE FROM EQUIPOS");
            stmt.executeUpdate("DELETE FROM SALAS");
            stmt.executeUpdate("DELETE FROM SOPORTE");
            stmt.executeUpdate("DELETE FROM SANCIONES");
            stmt.executeUpdate("DELETE FROM USUARIOS");

            // Eliminar y recrear secuencias (las secuencias al crearse inician en START WITH)
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

        } finally {
            // Siempre habilitar constraints FK al final
            stmt.executeUpdate("ALTER TABLE SOPORTE ENABLE CONSTRAINT FK_SOPORTE_USUARIO");
            stmt.executeUpdate("ALTER TABLE SOLICITUDES ENABLE CONSTRAINT FK_SOLICITUDES_USUARIO");
            stmt.executeUpdate("ALTER TABLE SANCIONES ENABLE CONSTRAINT FK_SANCION_USUARIO");
            stmt.close();
        }
    }
    public static List<Equipos> obtenerEquiposDesdeSP() {
        List<Equipos> lista = new ArrayList<>();
        String call = "{ call SP_LISTAR_EQUIPOS(?) }";

        try (Connection conn = new ConexionOracle().conectar();
             CallableStatement cs = conn.prepareCall(call)) {

            cs.registerOutParameter(1, OracleTypes.CURSOR); // usa oracle.jdbc.OracleTypes
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                while (rs.next()) {
                    Equipos e = new Equipos();
                    e.setId(rs.getLong("id"));
                    e.setNombre(rs.getString("nombre"));
                    lista.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static List<Salas> obtenerSalasDesdeSP() {
        List<Salas> lista = new ArrayList<>();
        String call = "{ call SP_LISTAR_SALAS(?) }";

        try (Connection conn = new ConexionOracle().conectar();
             CallableStatement cs = conn.prepareCall(call)) {

            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                while (rs.next()) {
                    Salas s = new Salas();
                    s.setId(rs.getLong("id"));
                    s.setNombre(rs.getString("nombre"));
                    lista.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public static boolean crearUsuario(String nombre, String apellido, String email, String clave, String nivel) {
        boolean exito = false;
        String sql = "{call sp_crear_usuario(?, ?, ?, ?, ?, ?)}";
        try (Connection cn = new ConexionOracle().conectar();
             CallableStatement cs = cn.prepareCall(sql)) {
            cs.setString(1, nombre);
            cs.setString(2, apellido);
            cs.setString(3, email);
            cs.setString(4, clave);
            cs.setString(5, nivel);
            cs.registerOutParameter(6, java.sql.Types.INTEGER);

            cs.execute();
            int resultado = cs.getInt(6);
            exito = (resultado == 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public static boolean borrarUsuarioPorId(Long id) {
        boolean exito = false;
        String sql = "{call sp_borrar_usuario(?, ?)}";
        try (Connection cn = new ConexionOracle().conectar();
             CallableStatement cs = cn.prepareCall(sql)) {
            cs.setLong(1, id);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);

            cs.execute();
            int resultado = cs.getInt(2);
            exito = (resultado == 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public static boolean modificarUsuario(Long id, String nombre, String apellido, String email, String clave, String nivel) {
        boolean exito = false;
        String sql = "{call sp_modificar_usuario(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection cn = new ConexionOracle().conectar();
             CallableStatement cs = cn.prepareCall(sql)) {
            cs.setLong(1, id);
            cs.setString(2, nombre);
            cs.setString(3, apellido);
            cs.setString(4, email);
            cs.setString(5, clave);
            cs.setString(6, nivel);
            cs.registerOutParameter(7, java.sql.Types.INTEGER);

            cs.execute();
            int resultado = cs.getInt(7);
            exito = (resultado == 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
    public ResultSet listarUsuariosDesdeBD() {
    try {
        Connection cn = new ConexionOracle().conectar(); // usa tu método de conexión existente
        CallableStatement cs = cn.prepareCall("{call sp_listar_usuarios(?)}");
        cs.registerOutParameter(1, OracleTypes.CURSOR); // asegúrate de tener la librería de Oracle
        cs.execute();
        return (ResultSet) cs.getObject(1);
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
    }
    public List<Soporte> obtenerListaSoportes(EntityManager em) {
        try {
            TypedQuery<Soporte> query = em.createQuery(
                "SELECT s FROM Soporte s JOIN FETCH s.usuario ORDER BY s.id", Soporte.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean eliminarSoporte(Long idSoporte, EntityManager em) {
        try {
            em.getTransaction().begin();
            Soporte soporte = em.find(Soporte.class, idSoporte);
            if (soporte != null) {
                em.remove(soporte);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}