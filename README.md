# video-games-DevOps
Repositorio para practica integración continua y despliegue continuo con Microsoft Azure.

### Descripción
Este proyecto constará únicamente de 2 tablas en la base de datos. Creadores y Videojuegos
La base de datos a usar es H2 (base de datos en memoria).
El principal objetivo de este proyecto será el de realizar una integración continua con el pipeline en Azure
para realizar la compilación, la ejecucion de los test y la creación del archivo empaquetado .jar CI/CD.
Luego se procedera a crear su despliegue en los diferentes ambientes de trabajo como ambiente de producción, stage y test.

### 🛠️ ¿Qué funcionalidades tendrá?
#### CRUDs:

- Crear/editar/eliminar desarrolladoras

- Crear/editar/eliminar videojuegos (asociándolos a una desarrolladora)

- Listar videojuegos agrupados por desarrolladora (vista opcional, pero muy útil).

### 💡 Extras opcionales (funcionalidad futura):

- Filtrar videojuegos por plataforma

- Mostrar detalles de cada videojuego en una página individual

- Validaciones de formularios (ej: fechas válidas, campos requeridos)

- Paginación (si hay muchos registros)

## 🧠 Notas de Lógica y Aprendizaje
#### ✅ Método update() en VideoGameService

Este método fue retador. Se trataba de actualizar los datos de un videojuego, y mantener sincronizada la relación bidireccional con su Creator.

Pasos que seguí:

1. Buscar el VideoGame en base al id proporcionado.

2. Buscar el nuevo Creator por su id (lanzar excepción si no existe).

3. Actualizar campos simples (title, platform, releaseDate).

4. Comparar el Creator actual y el nuevo:
   - Si es el mismo, guardar sin modificar la relación.

   - Si es diferente:
        -   Quitar el videojuego del oldCreator.
        -   Agregarlo al newCreator.
        - Asignar el nuevo creador al videoGame.

Esto evita inconsistencias en la relación bidireccional.

#### ✅ Método save() en VideoGameService

Pasos importantes:

1. Validar que el Creator exista, si no, lanzar excepción.

2. Validar si ya existe un videojuego con ese título + plataforma:

   - Si existe, lanzar excepción.

   - Esto evita duplicados innecesarios.

3. Sincronizar la relación:

   - Usar creator.addVideoGame(videoGame) para mantener coherencia bidireccional.

    - Asignar el creator al videoGame.

### ✅ Agrupar videojuegos por Creator (Proyección)

Proyecciones en Spring Data JPA. En lugar de traer entidades completas, usé una interfaz con getters que mapean directamente los resultados de una consulta nativa.

Pasos:

1. Crear la proyección con los campos necesarios: creatorName, title, platform, etc.

2. Hacer una consulta nativa con alias, que devuelve los campos seleccionados.

3. En el servicio, agrupar los resultados usando Collectors.groupingBy(...).

Esto ayuda a optimizar rendimiento y traer solo la información necesaria, especialmente útil en listados grandes.