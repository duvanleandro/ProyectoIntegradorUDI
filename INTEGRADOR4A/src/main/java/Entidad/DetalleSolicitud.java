package Entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="detalle_solicitud")
public class DetalleSolicitud implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(length=500)
    private String descripcion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Column(length=50)
    private String estado;
    
    @ManyToOne
    @JoinColumn(name="solicitud_id")
    private Solicitud solicitud;
    
    public DetalleSolicitud() {
    }
    
    public DetalleSolicitud(String descripcion, Date fecha, String estado, Solicitud solicitud) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.solicitud = solicitud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

}