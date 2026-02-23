package sv.edu.udb.biblioteca;
// Ruta: src/main/java/sv/edu/udb/biblioteca/BibliotecaApplication.java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
        System.out.println(" La aplicación  ha arrancado con éxito");
    }

}
