package Logica;

import Conexion.ConexionOracle;
import Entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IniciarSesion {
    public Usuario validarCredenciales(String correo, String password) {
        ConexionOracle conex = new ConexionOracle();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            cn = conex.conectar();
            String sql = "SELECT id, nombre, email, nivel FROM usuarios WHERE email = ? AND clave = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));          // Asegúrate que el campo exista y esté bien en la tabla
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("email"));
                usuario.setRol(rs.getString("nivel"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null && !cn.isClosed()) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }
}
