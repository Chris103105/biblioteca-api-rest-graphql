package sv.edu.udb.biblioteca.service;

import sv.edu.udb.biblioteca.dto.LibroDTO;
import sv.edu.udb.biblioteca.entity.Libro;
import sv.edu.udb.biblioteca.repository.AutorRepository;
import sv.edu.udb.biblioteca.repository.CategoriaRepository;
import sv.edu.udb.biblioteca.repository.EditorialRepository;
import sv.edu.udb.biblioteca.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogoService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final EditorialRepository editorialRepository;
    private final CategoriaRepository categoriaRepository;


    //PERACIONES PARA LIBROS


    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }


    //  MÉTODOS DE MAPEO (Entity -> DTO)



    public LibroDTO convertirALibroDTO(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setIsbn(libro.getIsbn());
        dto.setPaginas(libro.getPaginas());
        dto.setPrecio(libro.getPrecio());

        // Extraemos solo el nombre del autor si existe
        if (libro.getAutor() != null) {
            dto.setNombreAutor(libro.getAutor().getNombreCompleto());
        }

        // Extraemos solo el nombre de la editorial si existe
        if (libro.getEditorial() != null) {
            dto.setNombreEditorial(libro.getEditorial().getNombre());
        }

        // Convertimos la lista de objetos Categoria a una lista de Strings (nombres)
        if (libro.getCategorias() != null) {
            List<String> nombresCategorias = libro.getCategorias().stream()
                    .map(categoria -> categoria.getNombre())
                    .toList();
            dto.setCategorias(nombresCategorias);
        }

        return dto;
    }
}
