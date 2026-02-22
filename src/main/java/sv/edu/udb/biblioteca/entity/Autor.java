package sv.edu.udb.biblioteca.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String nacionalidad;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore // Evita recursión infinita en REST
    private List<Libro> libros;
}