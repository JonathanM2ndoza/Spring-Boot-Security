# Spring-Boot-Security

Example of RESTful API with Spring Boot and Spring Security. JSON Web Tokens (JWT), JPA, DB PostgreSQL. Apache Commons logging used by Spring Boot for all internal logging.

### Start a PostgreSQL server instance with Docker Hub

jmendoza@jmendoza-ThinkPad-T420:~$ docker run -d --name postgres -e POSTGRES_PASSWORD=root.jmtizure.k201 postgres

![Screenshot](/prtsc/Spring-boot-security-1.png)

### Check the ip of your container and update the application.properties file

jmendoza@jmendoza-ThinkPad-T420:~$ docker inspect postgres

![Screenshot](/prtsc/Spring-boot-security-2.png)

![Screenshot](/prtsc/Spring-boot-security-3.png)

![Screenshot](/prtsc/Spring-boot-security-4.png)

### Create Users

![Screenshot](/prtsc/Spring-boot-security-5.png)

![Screenshot](/prtsc/Spring-boot-security-6.png)

### BD PostgreSQL

![Screenshot](/prtsc/Spring-boot-security-7.png)

![Screenshot](/prtsc/Spring-boot-security-8.png)
 
Note: I am using pgAdmin 4 (Container), is a GUI client for PostgreSQL

### Request for JSON Web Token (JWT)

![Screenshot](/prtsc/Spring-boot-security-9.png)

### Get User with JWT

![Screenshot](/prtsc/Spring-boot-security-10.png)

### Get User with JWT expired

![Screenshot](/prtsc/Spring-boot-security-11.png)
