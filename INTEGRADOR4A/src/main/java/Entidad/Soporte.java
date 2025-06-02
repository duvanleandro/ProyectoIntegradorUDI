// Soporte.java
package Entidad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "soporte")
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String estado;

    private String fechaReporte;

    @ManyToOne
    private Equipos equipo;

    @ManyToOne
    private Salas sala;

    // Getters y Setters
}