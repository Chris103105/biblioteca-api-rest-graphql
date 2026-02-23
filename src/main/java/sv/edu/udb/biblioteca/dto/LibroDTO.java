package sv.edu.udb.biblioteca.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {
    private Long id;
    private String titulo;
    private String isbn;
    private Integer paginas;
    private Double precio;

    // En lugar de objetos completos, enviamos solo los datos relevantes
    private String nombreAutor;
    private String nombreEditorial;

    // Una lista con solo los nombres de las categorías
    private List<String> categorias;
}
