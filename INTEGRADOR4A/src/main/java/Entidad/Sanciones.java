package Entidad;

import javax.persistence.*;

@Entity
@Table(name = "SANCIONES")
public class Sanciones {

    @Id
    @Column(name = "ID_SANCION")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "TIPO_SANCION", nullable = false)
    private String tipoSancion;

    @Column(name = "MENSAJE")
    private String mensaje;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoSancion() {
        return tipoSancion;
    }

    public void setTipoSancion(String tipoSancion) {
        this.tipoSancion = tipoSancion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
