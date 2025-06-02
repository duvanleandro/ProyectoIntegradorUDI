// Devolucion.java
package Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "devolucion")
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private String observaciones;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Prestamo prestamo;

    @OneToOne(mappedBy = "devolucion")
    private Sanciones sancion;

    // Getters y Setters
}
