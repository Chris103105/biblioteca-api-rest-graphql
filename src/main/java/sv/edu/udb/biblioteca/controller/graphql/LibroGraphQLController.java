package sv.edu.udb.biblioteca.controller.graphql;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sv.edu.udb.biblioteca.entity.Autor;
import sv.edu.udb.biblioteca.entity.Libro;
import sv.edu.udb.biblioteca.repository.AutorRepository;
import sv.edu.udb.biblioteca.service.CatalogoService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibroGraphQLController {

    private final CatalogoService catalogoService;
    // Inyectamos el repositorio de autores directo para la consulta listarAutores
    private final AutorRepository autorRepository;

    // 1. Mapea exactamente con "listarLibros: [Libro]" del schema.graphqls
    @QueryMapping
    public List<Libro> listarLibros() {
        // Fíjate que devolvemos la Entidad (Libro), no el DTO (LibroDTO)
        return catalogoService.obtenerTodosLosLibros();
    }

    // 2. Mapea con "obtenerLibroPorId(id: ID!): Libro"
    @QueryMapping
    public Libro obtenerLibroPorId(@Argument Long id) {
        return catalogoService.obtenerLibroPorId(id).orElse(null);
    }

    // 3. Mapea con "listarAutores: [Autor]"
    @QueryMapping
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }
}