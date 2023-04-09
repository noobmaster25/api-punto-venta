# Punto de Venta API REST
API REST de un punto de venta con autenticacion por jwt con roles



Este proyecto es una API REST para un punto de venta sencillo. Permite gestionar clientes, órdenes, detalles de órdenes, categorías y productos.

## Estado
`Actualmente en desarrollo`

## Tecnologías utilizadas
- `Java`
- `Spring Boot`
- `Spring Data JPA`
- `Spring Validation`
- `Spring Security`
- `JWT con JJWT`
- `MySQL`

## Requerimientos
- `Java 11 o superior`
- `Maven`
- `MySQL`

## Instalación
1. Clona este repositorio.
2. **Crea una base de datos donde se crearan las tablas al arrancar la aplicacion.**
3. Abre el archivo `src/main/resources/application.properties` y configura las credenciales de tu base de datos MySQL.
4. Abre una terminal y navega hasta la carpeta del proyecto.
5. Ejecuta el comando `mvn spring-boot:run` para iniciar la aplicación.

## Uso
La API REST tiene los siguientes endpoints:

### Clientes
- `GET /punto-venta-api/v0/clientes`: Obtiene la lista de todos los clientes.
- `GET /punto-venta-api/v0/clientes/{id}`: Obtiene un cliente por su ID.
- `POST /punto-venta-api/v0/clientes`: Crea un nuevo cliente.
- `PUT /punto-venta-api/v0/clientes/{id}`: Actualiza un cliente existente.
- `DELETE /punto-venta-api/v0/clientes/{id}`: Elimina un cliente por su ID.

### Órdenes
- `GET /punto-venta-api/v0/ordenes`: Obtiene la lista de todas las órdenes.
- `GET /punto-venta-api/v0/ordenes/{id}`: Obtiene una orden por su ID.
- `POST /punto-venta-api/v0/ordenes`: Crea una nueva orden.
- `PUT /punto-venta-api/v0/ordenes/{id}`: Actualiza una orden existente.
- `DELETE /punto-venta-api/v0/ordenes/{id}`: Elimina una orden por su ID.

### Detalles de órdenes
- `GET /punto-venta-api/v0/detalles-orden`: Obtiene la lista de todos los detalles de órdenes.
- `GET /punto-venta-api/v0/detalles-orden/{id}`: Obtiene un detalle de orden por su ID.
- `POST /punto-venta-api/v0/detalles-orden`: Crea un nuevo detalle de orden.
- `PUT /punto-venta-api/v0/detalles-orden/{id}`: Actualiza un detalle de orden existente.
- `DELETE /punto-venta-api/v0/detalles-orden/{id}`: Elimina un detalle de orden por su ID.

### Categorías
- `GET /punto-venta-api/v0/categorias`: Obtiene la lista de todas las categorías.
- `GET /punto-venta-api/v0/categorias/{id}`: Obtiene una categoría por su ID.
- `POST /punto-venta-api/v0/categorias`: Crea una nueva categoría.
- `PUT /punto-venta-api/v0/categorias/{id}`: Actualiza una categoría existente.
- `DELETE /punto-venta-api/v0/categorias/{id}`: Elimina una categoría por su ID.

### Productos
- `GET /punto-venta-api/v0/productos`: Obtiene la lista de todos los productos.
- `GET /punto-venta-api/v0/productos/{id}`: Obtiene un producto por su ID.
- `POST /punto-venta-api/v0/productos`: Crea un nuevo producto.
- `PUT /punto-venta-api/v0/productos/{id}`: Actualiza un producto existente.
- `DELETE /punto-venta-api/v0/productos/{id}`: Elimina un producto por su ID.

### Auth
- `POST /punto-venta-api/v0/auth/login`: Logeo para obtner el token.
- `POST /punto-venta-api/v0/auth/registro`: Registro con role user por defecto


Puedes utilizar una herramienta como Postman o Insomnia para realizar las solicitudes HTTP a la API REST.

**Ejemplo** `POST /punto-venta-api/v0/auth/registro`
[![registro.png](https://i.postimg.cc/zB0FzWGH/registro.png)](https://postimg.cc/FdkLGYch)

**Ejemplo** `POST /punto-venta-api/v0/auth/login`
[![logeo.png](https://i.postimg.cc/632pqx9V/logeo.png)](https://postimg.cc/WFVvYKqt)

**Ejemplo** `GET /punto-venta-api/v0/productos` Enviando token
[![todos.png](https://i.postimg.cc/SNybtbwZ/todos.png)](https://postimg.cc/jCk1LmqN)

## Contribuir
Si quieres contribuir a este proyecto, por favor sigue estas instrucciones:
1. Crea un fork del repositorio.
2. Crea una nueva rama en tu fork.
3. Realiza tus cambios y haz un commit.
4. Envía un pull request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para obtener más información.
