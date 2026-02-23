package sv.edu.udb.biblioteca.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.biblioteca.entity.Editorial;
import sv.edu.udb.biblioteca.repository.EditorialRepository;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
@RequiredArgsConstructor
public class EditorialRestController {

    private final EditorialRepository editorialRepository;

    @GetMapping
    public List<Editorial> listarEditoriales() {
        return editorialRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> obtenerEditorial(@PathVariable Long id) {
        return editorialRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Editorial guardarEditorial(@RequestBody Editorial editorial) {
        return editorialRepository.save(editorial);
    }
}