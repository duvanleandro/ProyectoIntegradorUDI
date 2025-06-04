package Entidad;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "soporte")
public class Soporte {
    @Id
    @Column(name="ID_SOPORTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soporte_seq")
    @SequenceGenerator(name = "soporte_seq", sequenceName = "SEQ_SOPORTE", allocationSize = 1)
    private Long id;

    @Column(name="MENSAJE", nullable = false)
    private String mensaje;

    @Column(name="FECHA_ENVIO")
    private LocalDate fechaEnvio;

    @Column(name = "TIPO_SOPORTE", nullable = false)
    private String tipoSoporte;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTipoSoporte() {
        return tipoSoporte;
    }

    public void setTipoSoporte(String tipoSoporte) {
        this.tipoSoporte = tipoSoporte;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
