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