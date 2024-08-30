# Biblioteca API

Esta es una aplicación para la administración de libros y autores, desarrollada con un backend en PL/SQL y una capa de servicios REST en Java utilizando Spring Boot.

## Requisitos

- Java 11 o superior
- Maven 3.6+
- Docker y Docker Compose

## Ejecución de la Aplicación

### 1. Clona el Repositorio

```bash
git clone https://github.com/isabelsamboni/biblioteca.git
cd biblioteca-api


### 2. Configurara la base de datos

docker run -d -p 1521:1521 -e ORACLE_ALLOW_REMOTE=true --name oracle-xe oracleinanutshell/oracle-xe-11g

#Acceder al contenedor:

docker exec -it oracle-xe bash

#Conectarse a la base de datos con SQL*PLUS

sqlplus sys/oracle as sysdba

#Crear un Usuario y Esquema

CREATE USER biblioteca IDENTIFIED BY biblioteca;
GRANT CONNECT, RESOURCE TO biblioteca;
ALTER USER biblioteca DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;

#Conectar a la base de datos

CONNECT biblioteca/biblioteca;

#Crear tablas

CREATE TABLE autores (
    id_autor NUMBER PRIMARY KEY,
    nombre_autor VARCHAR2(100) NOT NULL
);

CREATE TABLE libros (
    id_libro NUMBER PRIMARY KEY,
    titulo_libro VARCHAR2(200) NOT NULL,
    id_autor NUMBER,
    FOREIGN KEY (id_autor) REFERENCES autores(id_autor)
);


#Crear procedimiento con PL/SQL

CREATE OR REPLACE PROCEDURE insertar_autor(
    p_id_autor IN NUMBER,
    p_nombre_autor IN VARCHAR2
) AS
BEGIN
    INSERT INTO autores (id_autor, nombre_autor)
    VALUES (p_id_autor, p_nombre_autor);
END;
/

CREATE OR REPLACE PROCEDURE insertar_libro(
    p_id_libro IN NUMBER,
    p_titulo_libro IN VARCHAR2,
    p_id_autor IN NUMBER
) AS
BEGIN
    INSERT INTO libros (id_libro, titulo_libro, id_autor)
    VALUES (p_id_libro, p_titulo_libro, p_id_autor);
END;
/

CREATE OR REPLACE PROCEDURE actualizar_autor(
    p_id_autor IN NUMBER,
    p_nombre_autor IN VARCHAR2
) AS
BEGIN
    UPDATE autores
    SET nombre_autor = p_nombre_autor
    WHERE id_autor = p_id_autor;
END;
/

CREATE OR REPLACE PROCEDURE eliminar_libro(
    p_id_libro IN NUMBER
) AS
BEGIN
    DELETE FROM libros
    WHERE id_libro = p_id_libro;
END;
/

CREATE OR REPLACE FUNCTION consultar_autores RETURN SYS_REFCURSOR AS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR SELECT * FROM autores;
    RETURN v_cursor;
END;
/


#Persistencia de la base de datos en e docker

Se crea volumen:

docker volume create oracle-data

Modificar comando deejecución:

docker run -d -p 1521:1521 -e ORACLE_ALLOW_REMOTE=true --name oracle-xe -v oracle-data:/u01/app/oracle oracleinanutshell/oracle-xe-11g



#### **Crear el Dockerfile**

Tener un archivo `Dockerfile` en la raíz de tu proyecto:

```dockerfile
# Fase de construcción
FROM maven:3.8.1-jdk-11 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Fase de ejecución
FROM openjdk:11-jre-slim
COPY --from=build /app/target/biblioteca-api-0.0.1-SNAPSHOT.jar /usr/local/lib/biblioteca-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/biblioteca-api.jar"]

#Crear imagen docker

docker build -t tu-usuario/biblioteca-api .


#Correr el contenedor local

docker run -d -p 8080:8080 --name biblioteca-api tu-usuario/biblioteca-api




 

