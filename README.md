#  Sistema de Biblioteca: Análisis Comparativo API REST vs GraphQL


##  Integrantes del Grupo
* **Elisabet Beatriz Marroquín González:** [MG251360] 
* **Christopher Steven Jovel Beltran   :** [JB251834] 
* **Odaly Rachel Cruz Franco:** [CF252175] 
* **Giovanni Alberto Ruano Martínez:** [RM250065]

  
Este proyecto es una implementación backend desarrollada en **Java con Spring Boot** para gestionar el catálogo de una biblioteca. Su objetivo principal es servir como base práctica para comparar el desarrollo, consumo y rendimiento entre una **API REST tradicional** y una **API GraphQL**.

##  Tecnologías Utilizadas

* **Java** (JDK 17+)
* **Spring Boot** (Spring Web, Spring for GraphQL)
* **Spring Data JPA** (Capa de persistencia)
* **H2 Database** (Base de datos en memoria para desarrollo ágil)
* **Lombok** (Reducción de código repetitivo/boilerplate)
* **Maven** (Gestor de dependencias)

##  Cómo ejecutar el proyecto

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, VS Code).
3. Ejecuta la clase principal `BibliotecaApplication.java`.
4. El servidor arrancará en el puerto `8080` y la base de datos H2 se poblará automáticamente con datos de prueba (Autores, Editoriales, Categorías y Libros) gracias a la clase `DataSeeder`.

---

##  Guías de Uso

A continuación se detalla cómo interactuar con los diferentes componentes del sistema.

### 1. Base de Datos (H2 Console)
Se puede verificar las tablas y registros generados automáticamente por JPA ingresando a la consola web de H2.
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:bibliotecadb`
* **User Name:** `sa`
* **Password:** *(dejar en blanco)*

### 2. Consumo de la API REST
La API REST utiliza endpoints específicos para cada recurso y el patrón DTO (Data Transfer Object) para evitar la recursión infinita en las relaciones de base de datos.

Puede probar estos endpoints desde tu navegador o usando clientes como **Postman** o **Insomnia**:

* **Listar todos los libros (GET):**
  `http://localhost:8080/api/libros`
* **Obtener un libro específico (GET):**
  `http://localhost:8080/api/libros/1`
* **Listar Autores (GET):**
  `http://localhost:8080/api/autores`

*( La API también soporta operaciones POST, PUT y DELETE para el endpoint `/api/libros` enviando el cuerpo en formato JSON).*

### 3. Consumo de la API GraphQL
GraphQL utiliza un único punto de entrada y permite al cliente definir exactamente la estructura de los datos que necesita, eliminando el problema del *over-fetching*.

* **URL de la interfaz GraphiQL:** `http://localhost:8080/graphiql`

**Ejemplo de Consulta (Query):**
Pega la siguiente consulta en el panel izquierdo y presiona "Ejecutar" (Play) para obtener los libros únicamente con el título, precio y el nombre de su respectivo autor y categorías:

```graphql
query {
  listarLibros {
    titulo
    precio
    autor {
      nombreCompleto
    }
    categorias {
      nombre
    }
  }
}
