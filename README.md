# Story Creation Service Api
 Backend api to create user login and create stories
## Requirements

- mysql
- java

## Prerequistes
- Install mysql and  run the following command(table and db name are case sensitive in mysql)
  create database user_stories;
- If intellij is used install lombok, enable lombok annotation processing
    Preferences -> lombok plugin -> enable lombok plugin
  Copy application.properties.cfg to application.properties and set the properties
  Make sure jwt.secret is a very long string of more than 256 bytes.

## Swagger Api docs

- http://localhost:8080/v2/api-docs

## Validations

- Request fields validations are done using javax.validation framework


## Database migrations

- Flyway is used to manage db migration. It runs automatically on app startup


## Dependency Injection

- Spring Autowired is used for dependency injection


## build and test

```
  Check application.properties for right mysql username,password,port(default: 3306)
  mvn clean install
```

## run app

```
 mvn spring-boot:run

```
