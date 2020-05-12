# Spring-Boot-Security

Example of RESTful API with Spring Boot and Spring Security. Securing URLs using JSON Web Tokens (JWT) and GrantedAuthority, JPA, DB PostgreSQL, Log4j2 Asynchronous Loggers. Swagger for RESTful API Documentation. 

### Start a PostgreSQL server instance with Docker Hub

jmendoza@jmendoza-ThinkPad-T420:~$ docker run -d --name postgres -e POSTGRES_PASSWORD=root.jmtizure.k201 postgres

![Screenshot](/prtsc/Spring-boot-security-1.png)

### Check the ip of your container and update the application.properties file

jmendoza@jmendoza-ThinkPad-T420:~$ docker inspect postgres

![Screenshot](/prtsc/Spring-boot-security-2.png)

![Screenshot](/prtsc/Spring-boot-security-3.png)

![Screenshot](/prtsc/Spring-boot-security-4.png)

### Sign Up - New Users

![Screenshot](/prtsc/Spring-boot-security-5.png)

![Screenshot](/prtsc/Spring-boot-security-6.png)

### BD PostgreSQL

Table users 

![Screenshot](/prtsc/Spring-boot-security-8.png)

Table user_role

![Screenshot](/prtsc/Spring-boot-security-8.1.png)

Table role

![Screenshot](/prtsc/Spring-boot-security-8.2.png)
 
Note: I am using pgAdmin 4 (Container), is a GUI client for PostgreSQL

### Sign In - Request for JSON Web Token (JWT)

![Screenshot](/prtsc/Spring-boot-security-9.png)

### Get User with JWT and ROLE_ADMIN Role

![Screenshot](/prtsc/Spring-boot-security-10.png)

### Get User with JWT expired

![Screenshot](/prtsc/Spring-boot-security-11.png)

### Get User with JWT and ROLE_USER Role

![Screenshot](/prtsc/Spring-boot-security-11-1.png)

![Screenshot](/prtsc/Spring-boot-security-11-2.png)


## Swagger (Pending Update)

http://localhost:8083/swagger-ui.html#/

![Screenshot](/prtsc/Spring-boot-security-12.png)

Controllers

![Screenshot](/prtsc/Spring-boot-security-13.png)

![Screenshot](/prtsc/Spring-boot-security-14.png)

Try it out

![Screenshot](/prtsc/Spring-boot-security-15.png)

![Screenshot](/prtsc/Spring-boot-security-16.png)

Models

![Screenshot](/prtsc/Spring-boot-security-17.png)