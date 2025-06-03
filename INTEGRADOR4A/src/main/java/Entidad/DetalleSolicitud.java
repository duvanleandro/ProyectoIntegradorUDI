package Entidad;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "detalle_solicitud")
public class DetalleSolicitud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_solicitud_seq")
    @SequenceGenerator(name = "detalle_solicitud_seq", sequenceName = "seq_detalle_solicitud", allocationSize = 1)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "tipo_servicio", nullable = false, length = 50)
    private String tipoServicio;

    @Column(name = "elemento", nullable = false, length = 100)
    private String elemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(String tipoServicio, String elemento, Solicitud solicitud) {
        this.tipoServicio = tipoServicio;
        this.elemento = elemento;
        this.solicitud = solicitud;
    }

    // Getters y Setters

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
}
