package sv.edu.udb.biblioteca;
// Ruta: src/main/java/sv/edu/udb/biblioteca/DataSeeder.java

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sv.edu.udb.biblioteca.entity.Autor;
import sv.edu.udb.biblioteca.entity.Categoria;
import sv.edu.udb.biblioteca.entity.Editorial;
import sv.edu.udb.biblioteca.entity.Libro;
import sv.edu.udb.biblioteca.repository.AutorRepository;
import sv.edu.udb.biblioteca.repository.CategoriaRepository;
import sv.edu.udb.biblioteca.repository.EditorialRepository;
import sv.edu.udb.biblioteca.repository.LibroRepository;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AutorRepository autorRepository;
    private final EditorialRepository editorialRepository;
    private final CategoriaRepository categoriaRepository;
    private final LibroRepository libroRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Verificamos si ya hay datos para no duplicarlos en caso de que cambies
        // el ddl-auto a 'update' y reinicies varias veces.
        if (libroRepository.count() == 0) {

            // 2. Crear Autores
            Autor autor1 = new Autor(null, "Gabriel García Márquez", "Colombiana", "Premio Nobel de Literatura 1982.", null);
            Autor autor2 = new Autor(null, "J.K. Rowling", "Británica", "Autora de la famosa saga de magia.", null);
            autorRepository.saveAll(Arrays.asList(autor1, autor2));

            // 3. Crear Editoriales
            Editorial editorial1 = new Editorial(null, "Editorial Sudamericana", "Argentina", "www.sudamericana.com", null);
            Editorial editorial2 = new Editorial(null, "Bloomsbury", "Reino Unido", "www.bloomsbury.com", null);
            editorialRepository.saveAll(Arrays.asList(editorial1, editorial2));

            // 4. Crear Categorías
            Categoria catFiccion = new Categoria(null, "Ficción", "Obras literarias basadas en situaciones imaginarias.", null);
            Categoria catFantasia = new Categoria(null, "Fantasía", "Obras que contienen elementos mágicos o sobrenaturales.", null);
            Categoria catRealismo = new Categoria(null, "Realismo Mágico", "Muestra lo irreal como cotidiano.", null);
            categoriaRepository.saveAll(Arrays.asList(catFiccion, catFantasia, catRealismo));

            // 5. Crear Libros
            Libro libro1 = new Libro(null, "Cien Años de Soledad", "978-0307474728", 417, 15.99,
                    autor1, editorial1, Arrays.asList(catFiccion, catRealismo));

            Libro libro2 = new Libro(null, "Harry Potter y la Piedra Filosofal", "978-8498384376", 254, 12.50,
                    autor2, editorial2, Arrays.asList(catFiccion, catFantasia));

            libroRepository.saveAll(Arrays.asList(libro1, libro2));

            System.out.println(" Datos  cargados  en la base de datos H2.");
        }
    }
}