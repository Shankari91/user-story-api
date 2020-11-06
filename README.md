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


## Validations

- Email field and other validation is done using javax.validation framework


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
