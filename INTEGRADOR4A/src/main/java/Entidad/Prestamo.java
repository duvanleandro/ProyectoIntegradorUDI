// Prestamo.java
package Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaInicio;
    private String fechaFin;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Equipos equipo;

    @ManyToOne
    private Salas sala;

    @OneToOne(mappedBy = "prestamo")
    private Devolucion devolucion;

    // Getters y Setters
}