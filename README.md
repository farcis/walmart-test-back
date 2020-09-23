# Walmart Test Back

Este proyecto es un backend endpoint desarrollado como prueba para Walmart.
Esta construido en Java Spring Boot y se puede ver en Heroku a traves de la siguiente [URL](https://vast-beyond-44426.herokuapp.com/ws/api/v1/search/123) 

Por debajo se conecta a una Base de datos Mongo.

## Dependencias

- Docker
- Java
- Mongo

## Scripts

Este proyecto consta de un set de comandos para ayudar levantar e interactuar con el proyecto

### `make clean`

### `make assemble`

### `make run`

Levanta la aplicacion en local que se puede consultar en la url [http://localhost;8080/ws/api/v1/search/query](http://localhost:8080/ws/api/v1/search/query)

### `make unit-test`

Corre los test asociados al proyecto.

### `make integration-test`

corre los test de integracion asociados al proyecto, para poder correrlos es necesario tener levantado el docker de la base de datos de esta [URL]((https://github.com/walmartdigital/products-db/))

### `make test`

corre tanto los test unitarios como los de integracion

### `make docker-run`

levanta contenedor docker que se puede consultar en la url [http://localhost;8080/ws/api/v1/search/query](http://localhost:8080/ws/api/v1/search/query) 


## Supuestos

- solo seran evaluados como palindormes aquellas query string de largo mayor a 3.
- de argo 4 en adelante se buscaran coincidencias tanto en marca como en descripción.
- si la búsqueda es númerica independiente del largo solo busca por id. 





