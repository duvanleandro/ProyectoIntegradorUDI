package Logica;

import Conexion.ConexionOracle;
import Entidad.*;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RealizarSolicitud {

    private EntityManagerFactory emf;
    private EntityManager em;

    public RealizarSolicitud() {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }

    public static class DetalleTemp {
        private String tipoServicio;
        private String elemento;

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

    private List<DetalleTemp> listaDetallesTemp = new ArrayList<>();

    public List<Equipos> listarEquipos() {
        List<Equipos> resultado = new ArrayList<>();
        String sql = "SELECT id, nombre FROM equipos";

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

    public List<Salas> listarSalas() {
        List<Salas> resultado = new ArrayList<>();
        String sql = "SELECT id, nombre FROM salas";

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

    public void agregarDetalleTemporal(String tipoServicio, String elemento) {
        listaDetallesTemp.add(new DetalleTemp(tipoServicio, elemento));
    }

    public List<DetalleTemp> getListaDetallesTemp() {
        return listaDetallesTemp;
    }

    public void eliminarDetalleTemporal(int indice) {
        if (indice >= 0 && indice < listaDetallesTemp.size()) {
            listaDetallesTemp.remove(indice);
        }
    }

    public boolean registrarSolicitudJPA(Long idUsuario, java.util.Date fechaSolicitud,
                                         java.util.Date fechaUso, String horaInicio, String horaFin, String estado) {
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

    public boolean cancelarSolicitud(Long idSolicitud) {
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
        }
        return exito;
    }

    public void cerrar() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}
