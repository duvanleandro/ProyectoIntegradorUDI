package Entidad;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ENCUESTAS")
public class Encuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_encuesta")
    @SequenceGenerator(name = "seq_encuesta", sequenceName = "SEQ_ENCUESTA", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "PREGUNTA1", nullable = false, length = 500)
    private String pregunta1;

    @Column(name = "PREGUNTA2", nullable = false, length = 500)
    private String pregunta2;

    @Column(name = "PREGUNTA3", nullable = false, length = 500)
    private String pregunta3;

    public Encuesta() {}

    public Encuesta(Usuario usuario, String pregunta1, String pregunta2, String pregunta3) {
        this.usuario = usuario;
        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
        this.pregunta3 = pregunta3;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getPregunta1() { return pregunta1; }
    public void setPregunta1(String pregunta1) { this.pregunta1 = pregunta1; }

    public String getPregunta2() { return pregunta2; }
    public void setPregunta2(String pregunta2) { this.pregunta2 = pregunta2; }

    public String getPregunta3() { return pregunta3; }
    public void setPregunta3(String pregunta3) { this.pregunta3 = pregunta3; }
}
