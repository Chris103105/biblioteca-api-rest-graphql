package sv.edu.udb.biblioteca.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.biblioteca.entity.Autor;
import sv.edu.udb.biblioteca.repository.AutorRepository;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
public class  AutorRestController {

    private final AutorRepository autorRepository;

    @GetMapping
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutor(@PathVariable Long id) {
        return autorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Autor guardarAutor(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }
}