package Logica;

import Conexion.ConexionOracle;
import Entidad.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarUsuarios {

    public boolean crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nombre, apellido, email, clave, nivel) " +
             "VALUES (seq_usuarios_id.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection cn = new ConexionOracle().conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());  
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getClave());    
            ps.setString(5, usuario.getRol());
            



            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

public boolean borrarUsuarioPorId(Long id) {
    String sql = "DELETE FROM usuarios WHERE id = ?";
    try (Connection cn = new ConexionOracle().conectar();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setLong(1, id);
        int filas = ps.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


   public boolean modificarUsuario(Usuario usuario) {
    String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, clave = ?, nivel = ? WHERE id = ?";
    try (Connection cn = new ConexionOracle().conectar();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getCorreo());
        ps.setString(4, usuario.getClave());
        ps.setString(5, usuario.getRol());
        ps.setLong(6, usuario.getId());

        int filas = ps.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        // Cambié "usuario" por "usuarios" y agregué apellido y clave en el select
        String sql = "SELECT id, nombre, apellido, email AS correo, clave, nivel AS rol FROM usuarios";

        try (Connection cn = new ConexionOracle().conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCorreo(rs.getString("correo"));
                u.setClave(rs.getString("clave"));
                u.setRol(rs.getString("rol"));
                usuarios.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

}
