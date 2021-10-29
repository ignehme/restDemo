# Rest service application

## Introduction
The objective for this service is to manage prices.

## Installation and run

We use Contract First approach, so you need to generate an api.yaml file in src/main/resources with the definition of the API in OpenAPI format (Swagger file).

In the package phase of the Maven lifecycle, the DTOs and driver interfaces will be auto-generated as specified in src/main/resources/api.yaml. During the clean phase, all self-generated DTOs and driver interfaces will be removed

To start the microservice with the local configuration you can run the following command:

```
mvn spring-boot:run
```

### build application image
```
docker build -t rest-demo:1.0 .
```

### build & run container
```
docker run -p 8088:8088  rest-demo:1.0
```

### orchestrate containers
This service depends on a Database connection.

You can test the service on a local environment using the file application.yml. To do that, you'll need a Postgres database. So we suggest to use docker containers, running a:

```
docker compose up
```

on the docker-compose.yml file located at the root of the project:

```
version: '2.1'
services:
  postgresql:
    image: postgres:11
    restart: always
    environment:
      POSTGRES_DB: dbTest
      POSTGRES_USER: postgres
      POSTGRES_HOST_AUTH_METHOD: trust
    ports:
      - 5432:5432

  restDemoApp:
    image: rest-demo:1.0
    restart: always
    ports:
      - 8088:8088
    depends_on:
      - postgresql
```

- In order to check the data in the database you can use the psql client:

```
psql -h localhost -U postgres
```

Once installed and started, you can take a look to the service's API with the swagger interface:

```
http://localhost:8088/swagger-ui/#
```
