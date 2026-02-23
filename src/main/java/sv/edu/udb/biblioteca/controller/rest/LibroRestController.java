
package sv.edu.udb.biblioteca.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.biblioteca.dto.LibroDTO;
import sv.edu.udb.biblioteca.entity.Libro;
import sv.edu.udb.biblioteca.service.CatalogoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroRestController {

    private final CatalogoService catalogoService;

    // 1. Obtener todos los libros
    @GetMapping
    public List<LibroDTO> listarLibros() {
        List<Libro> libros = catalogoService.obtenerTodosLosLibros();

        // Convertimos cada Entidad Libro a un LibroDTO usando tu servicio
        return libros.stream()
                .map(catalogoService::convertirALibroDTO)
                .collect(Collectors.toList());
    }

    // 2. Obtener un libro en específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtenerLibro(@PathVariable Long id) {
        Optional<Libro> libro = catalogoService.obtenerLibroPorId(id);

        if (libro.isPresent()) {
            LibroDTO dto = catalogoService.convertirALibroDTO(libro.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. Guardar un nuevo libro
    @PostMapping
    public ResponseEntity<LibroDTO> guardarLibro(@RequestBody Libro libro) {
        Libro libroGuardado = catalogoService.guardarLibro(libro);
        LibroDTO dto = catalogoService.convertirALibroDTO(libroGuardado);

        return new ResponseEntity<>(dto, HttpStatus.CREATED); // Devuelve un 201 Created
    }

    // 4. Eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        catalogoService.eliminarLibro(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content
    }


    // 5. Actualizar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Optional<Libro> libroExistente = catalogoService.obtenerLibroPorId(id);

        if (libroExistente.isPresent()) {
            // Aseguramos que el ID sea el correcto antes de guardar
            libroActualizado.setId(id);
            Libro libroGuardado = catalogoService.guardarLibro(libroActualizado);
            LibroDTO dto = catalogoService.convertirALibroDTO(libroGuardado);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}