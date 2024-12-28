[![Java CI with Gradle](https://github.com/MarkMngoma/ratpack-webauthn/actions/workflows/gradle.yml/badge.svg)](https://github.com/MarkMngoma/ratpack-webauthn/actions/workflows/gradle.yml)
# **Reactive Ratpack WebAuthn API** 🚀

Welcome to the **Reactive Ratpack webauthn** API! This is a powerful API for managing and querying authentication resources with cutting-edge features built using **Ratpack (Java 17) and Google Guice**.

The API allows you to perform CRUD operations on biometric web authentication resources, including Apple Face Id, FIDO, and more! 🌍

## Features 🌟

- **WriteAuthenticationResource**
- **QueryChallengeResource**
- **Swagger Spec**: Full OpenAPI 3.0 specification to help you integrate smoothly.

## 📜 API Documentation

The API exposes several endpoints that you can use to interact with the authentication resources:

### 📝 **Swagger Spec (OpenAPI 3.0)**

You can find the complete Swagger spec for the API in the `spec.yaml` file. Here's a preview:

```yaml
openapi: 3.0.3
info:
  title: reactive-webauthn
  version: 1.0.0
  contact: {}
servers:
  - url: https://localhost:5051
paths:
  /v1/WriteAuthenticationResource:
    post:
      tags:
        - V1
      summary: /v1/WriteAuthenticationResource
      description: /v1/WriteAuthenticationResource
      operationId: v1WriteAuthenticationResource
      requestBody:
        content:
          application/json:
            schema:
              type: object

            examples:
              /v1/WriteAuthenticationResource:

      responses:
        '200':
          description: ''
  /v1/QueryChallengeResource:
    get:
      tags:
        - V1
      summary: /v1/QueryChallengeResource
      description: /v1/QueryChallengeResource
      operationId: v1QueryChallengeResource
      responses:
        '200':
          description: ''

  /v1/WriteRegistrationResource:
    post:
      tags:
        - V1
      summary: /v1/WriteRegistrationResource
      description: /v1/WriteRegistrationResource
      operationId: v1WriteRegistrationResource
      requestBody:
        content:
          application/json:
            schema:
              type: object

            examples:
              /v1/WriteRegistrationResource:
                value:

      responses:
        '200':
          description: ''
tags:
  - name: V1
```

## 🚀 Getting Started

To get started with **Reactive Ratpack webauthn API**, follow the steps below to set up the project locally or in a containerized environment.

### 🐳 Spinning Up the Database Container

The project uses **Docker** for containerization. To run the application in a container, you need to spin up the Docker container using the provided `docker-compose.yml` file.

1. **Spin Up the localhost Containers:**

   Run the following command to start the containers:

   ```bash
   docker compose -f ./src/test/resources/docker-compose.yml up
   ```

   Run the following command to start the containers in detached mode:

   ```bash
   docker compose -f ./src/test/resources/docker-compose.yml up -d
   ```

2. **Shut Down the Containers:**

   Run the following command to stop the containers:

   ```bash
   docker compose -f ./src/test/resources/docker-compose.yml down
   ```

   This will start all the necessary services defined in the `docker-compose.yml` file.

---

### 🖥️ Running the API on Localhost

1. **Clone the Repository:**

   Clone the repository to your local machine:

   ```bash
   git clone git@github.com:MarkMngoma/reactive-webauthn-ratpack.git
   cd reactive-webauthn-ratpack
   ```

2. **Compile ShadowJar:**

   This will automatically pull the required modules

   ```bash
   ./gradlew clean test shadowJar
   ```

3. **Export Environment Variable:**

   This will be read as a java environment variable

   ```bash
   export RATPACK_ENVIRONMENT=localhost
   ```

4. **Start the API Server locally:**

   Using the jar artifact:
   ```bash
   java -jar ./build/libs/reactive-webauthn-ratpack.jar
   ```

   Using gradle run task
    ```bash
    ./gradlew clean run
    ```

   The API will now be available at [http://localhost:5051]([http://localhost:5051).

---

### 🔬 Running Integration Tests

The project includes integration tests to ensure that everything is functioning as expected.

1. **Run Tests Using Gradle CLI:**

   To run the integration tests, use the following command:

   ```bash
   ./gradlew clean test
   ```

   This will execute all the integration tests and display the results in the terminal.

2. **Test Environment Setup:**

   Make sure the API is running before executing the tests, especially when working with containers. You can also run the tests in isolation or as part of your CI/CD pipeline.

---

Happy coding! 🎉🚀
