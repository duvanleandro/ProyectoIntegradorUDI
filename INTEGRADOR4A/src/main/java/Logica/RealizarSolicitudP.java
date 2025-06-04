package Logica;

import Conexion.ConexionOracle;
import Entidad.DetalleSolicitud;
import Entidad.Equipos;
import Entidad.Salas;
import Entidad.Solicitud;
import Entidad.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class RealizarSolicitudP {

    // Clase interna para guardar en memoria cada fila que se mostrará en TablaVisual:
    public static class DetalleTemp {
        private String tipoServicio; // “Equipo Audiovisual” o “Sala de Informática”
        private String elemento;     // nombre de equipo o sala

        public DetalleTemp(String tipoServicio, String elemento) {
            this.tipoServicio = tipoServicio;
            this.elemento = elemento;
        }

        public String getTipoServicio() {
            return tipoServicio;
        }

        public String getElemento() {
            return elemento;
        }
    }

    // Lista en memoria de detalles que el usuario “agrega” antes de enviar
    private List<DetalleTemp> listaDetallesTemp = new ArrayList<>();
    
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public RealizarSolicitudP() {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();    
    }
    public boolean registrarSolicitudJPA(
        Long idUsuario,
        java.util.Date fechaSolicitud,
        java.util.Date fechaUso,
        String horaInicio,
        String horaFin,
        String estado
) {
    boolean todoOK = false;
    try {
        em.getTransaction().begin();

        Usuario usuario = em.find(Usuario.class, idUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe usuario con ID: " + idUsuario);
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario);
        solicitud.setFechaSolicitud(fechaSolicitud);
        solicitud.setFechaUso(fechaUso);
        solicitud.setHoraInicio(horaInicio);
        solicitud.setHoraFin(horaFin);
        solicitud.setEstado(estado);

        em.persist(solicitud);

        for (DetalleTemp dt : listaDetallesTemp) {
            DetalleSolicitud detalle = new DetalleSolicitud();
            detalle.setTipoServicio(dt.getTipoServicio());
            detalle.setElemento(dt.getElemento());
            detalle.setSolicitud(solicitud);
            em.persist(detalle);
        }

        em.getTransaction().commit();
        todoOK = true;
        listaDetallesTemp.clear();

    } catch (Exception ex) {
        ex.printStackTrace();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
    return todoOK;
}


        public void cerrar() {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }

    // 1) Listar todos los equipos disponibles desde la tabla EQUIPOS
public List<Equipos> listarEquipos() {
    List<Equipos> resultado = new ArrayList<>();
    String sql = "SELECT id, nombre FROM equipos";  // solo id y nombre

    try (Connection cn = new ConexionOracle().conectar();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Equipos e = new Equipos();
            e.setId(rs.getLong("id"));
            e.setNombre(rs.getString("nombre"));
            resultado.add(e);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return resultado;
}

// 2) Listar todas las salas disponibles desde la tabla SALAS
public List<Salas> listarSalas() {
    List<Salas> resultado = new ArrayList<>();
    String sql = "SELECT id, nombre FROM salas";  // solo id y nombre

    try (Connection cn = new ConexionOracle().conectar();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Salas s = new Salas();
            s.setId(rs.getLong("id"));
            s.setNombre(rs.getString("nombre"));
            resultado.add(s);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return resultado;
}


    // 3) Agregar un detalle temporal (antes de “Enviar”):
    public void agregarDetalleTemporal(String tipoServicio, String elemento) {
        listaDetallesTemp.add(new DetalleTemp(tipoServicio, elemento));
    }

    // Obtener la lista en memoria (para que la GUI la muestre en JTable)
    public List<DetalleTemp> getListaDetallesTemp() {
        return listaDetallesTemp;
    }

    // Permite borrar un detalle puntual de la lista en memoria (por índice)
    public void eliminarDetalleTemporal(int indice) {
        if (indice >= 0 && indice < listaDetallesTemp.size()) {
            listaDetallesTemp.remove(indice);
        }
    }

    // 4) Registrar toda la solicitud (cabecera + detalles) en la BD
public boolean registrarSolicitud(
        Long idUsuario,
        java.sql.Date fechaSolicitud,
        java.sql.Date fechaUso,
        String horaInicio,
        String horaFin,
        String estado
) {
    Connection cn = null;
    PreparedStatement psInsertSolicitud = null;
    PreparedStatement psInsertDetalle = null;
    boolean todoOK = false;

    try {
        cn = new ConexionOracle().conectar();
        cn.setAutoCommit(false); // inicio transacción

        String sqlInsertSolicitud =
        "BEGIN "
      + "INSERT INTO solicitudes "
      + "(id_solicitud, id_usuario, fecha_solicitud, fecha_uso, hora_inicio, hora_fin, estado) "
      + "VALUES (seq_solicitudes_id.NEXTVAL, ?, ?, ?, ?, ?, ?) "
      + "RETURNING id_solicitud INTO ?; "
      + "END;";

        CallableStatement cs = cn.prepareCall(sqlInsertSolicitud);
        cs.setLong(1, idUsuario);
        cs.setDate(2, fechaSolicitud);
        cs.setDate(3, fechaUso);
        cs.setString(4, horaInicio);
        cs.setString(5, horaFin);
        cs.setString(6, estado);
        cs.registerOutParameter(7, java.sql.Types.NUMERIC);

        cs.execute();
        Long idGenerado = cs.getLong(7);
        System.out.println("ID generado de solicitud: " + idGenerado);

        // 3) Verificar si el ID realmente existe en la tabla SOLICITUDES
        String sqlVerificar = "SELECT COUNT(*) FROM solicitudes WHERE id_solicitud = ?";
        try (PreparedStatement psVerificar = cn.prepareStatement(sqlVerificar)) {
            psVerificar.setLong(1, idGenerado);
            try (ResultSet rsVerificar = psVerificar.executeQuery()) {
                if (rsVerificar.next() && rsVerificar.getInt(1) == 0) {
                    throw new SQLException("El ID generado no existe en la tabla SOLICITUDES.");
                }
            }
        }

        // 4) Insertar cada detalle en DETALLE_SOLICITUD
        String sqlInsertDetalle =
            "INSERT INTO detalle_solicitud "
          + "(id_detalle, id_solicitud, tipo_servicio, elemento) "
          + "VALUES (seq_detalle_solicitud.NEXTVAL, ?, ?, ?)";

        psInsertDetalle = cn.prepareStatement(sqlInsertDetalle);

        for (DetalleTemp dt : listaDetallesTemp) {
            System.out.println("Agregando detalle: " + dt.getTipoServicio() + " - " + dt.getElemento());
            psInsertDetalle.setLong(1, idGenerado);
            psInsertDetalle.setString(2, dt.getTipoServicio());
            psInsertDetalle.setString(3, dt.getElemento());
            psInsertDetalle.addBatch();
        }

        psInsertDetalle.executeBatch();
        cn.commit();
        todoOK = true;

        listaDetallesTemp.clear(); // solo si todo salió bien

    } catch (SQLException ex) {
        ex.printStackTrace();
        if (cn != null) {
            try {
                cn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        todoOK = false;
    } finally {
        try {
            if (psInsertDetalle != null) psInsertDetalle.close();
            if (psInsertSolicitud != null) psInsertSolicitud.close();
            if (cn != null && !cn.isClosed()) cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return todoOK;
}

public boolean cancelarSolicitud(Long idSolicitud) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();
    boolean exito = false;

    try {
        em.getTransaction().begin();

        Solicitud solicitud = em.find(Solicitud.class, idSolicitud);

        if (solicitud != null && "PENDIENTE".equalsIgnoreCase(solicitud.getEstado())) {
            solicitud.setEstado("CANCELADO");
            em.merge(solicitud);
            em.getTransaction().commit();
            exito = true;
        } else {
            em.getTransaction().rollback();
        }

    } catch (Exception e) {
        e.printStackTrace();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    } finally {
        em.close();
        emf.close();
    }

    return exito;
}

public boolean aceptarSolicitud(Long idSolicitud) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();
    boolean exito = false;

    try {
        em.getTransaction().begin();

        Solicitud solicitud = em.find(Solicitud.class, idSolicitud);

        if (solicitud != null && "PENDIENTE".equalsIgnoreCase(solicitud.getEstado())) {
            solicitud.setEstado("ACEPTADO");
            em.merge(solicitud);
            em.getTransaction().commit();
            exito = true;
        } else {
            em.getTransaction().rollback();
        }

    } catch (Exception e) {
        e.printStackTrace();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    } finally {
        em.close();
        emf.close();
    }

    return exito;
}

public boolean rechazarSolicitud(Long idSolicitud) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();
    boolean exito = false;

    try {
        em.getTransaction().begin();

        Solicitud solicitud = em.find(Solicitud.class, idSolicitud);

        if (solicitud != null && "PENDIENTE".equalsIgnoreCase(solicitud.getEstado())) {
            solicitud.setEstado("RECHAZADO");
            em.merge(solicitud);
            em.getTransaction().commit();
            exito = true;
        } else {
            em.getTransaction().rollback();
        }

    } catch (Exception e) {
        e.printStackTrace();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    } finally {
        em.close();
        emf.close();
    }

    return exito;
}

}