# PQR

[![maven](https://img.shields.io/badge/maven-v3.6.X-red.svg)](https://maven.apache.org/)

>Projecto en Maven Java 8 con spring boot, actuator, spring data, spring web y una base de datos en Mongo
>
>Se utiliza lombok para optimizar codigo
>
>Arquitectura MVC
>
>>Creacion PQR
> 
>POST http://localhost:9091/pqr/ 
>
>>Obtener PQR por id
> 
>GET http://localhost:9091/pqr/{id}
>
>>Obtener PQR por username
> 
>GET http://localhost:9091/pqr/by-username/{username}
>
>>Obtener PQR Abiertos
> 
>GET http://localhost:9091/pqr/open
>
>>Actualizar PQR respuesta
> 
>PUT http://localhost:9091/pqr/
> 
>> JSON POSTMAN
>
> pqr.postman_collection.json

# Prerequisitos

* [Git](http://git-scm.com/)
* [Maven](https://maven.apache.org/)

# Test

* [Junit](https://junit.org/junit4/)

> mvn test
>>target/site/jacoco/index.html

# Build

> mvn verify

# RUN

> mvn verify
> 
> docker-compose build && docker-compose up
> 
> MongoDB localhost 27017
> 
> APP REST localhost 9091
