# Barber Shop Management System

![Java](https://img.shields.io/badge/Java-21-blue) ![Maven](https://img.shields.io/badge/Maven-3.x-red)

A simple CLI application for managing barbershop services, tracking clients, and calculating total revenue with a clean Java domain model.

## About

This project was designed as a small business management application for a barber shop. It allows users to register haircuts and beard trims, list all services, and calculate the total revenue generated during the current session. The application stores the service list in a local JSON file so it can be restored between executions.

## Features

- Register haircuts and beard services
- Calculate revenue using `BigDecimal`
- Persist service data in `dados.json`
- Validate invalid client names and invalid monetary values
- Protect the internal service list from external mutation
- Use a simple console menu for user interaction

## Tech Stack

- Java 21
- Maven
- JUnit 5
- Jackson Databind
- Console-based CLI

## How to Run

```bash
mvn clean test
mvn package
java -cp target/classes barbearia.cli.BarbeariaSystem
```

## Business Rules

- Client names cannot be null or blank
- Descriptions cannot be null or blank
- Monetary values cannot be negative
- Haircut base price is `35.0`
- Beard base price is `20.0` plus optional additional value
- Total revenue is computed as the sum of all service prices

## Project Structure

```text
barbearia/
├── cli/
│   └── BarbeariaSystem.java
├── domain/
│   ├── Servico.java
│   ├── CorteCabelo.java
│   └── CorteBarba.java
├── exception/
│   ├── ClienteInvalidoException.java
│   └── ServicoInvalidoException.java
├── service/
│   ├── GerenciadorBarbearia.java
│   └── PersistenciaService.java
└── ...

src/
├── main/java/barbearia/...
├── test/java/barbearia/domain/ServicoTest.java
├── test/java/barbearia/service/GerenciadorBarbeariaTest.java
└── dados.json
```

## Architecture Diagram

```text
+-------------------+
|  barbearia.cli    |
|  BarbeariaSystem  |
|  (user interface) |
+---------+---------+
          |
          v
+---------+---------+
| barbearia.service |
| GerenciadorBarbearia |
| + persist data     |
| + calculate total  |
+---------+---------+
          |
          v
+---------+---------+
| barbearia.domain  |
| Servico            |
| CorteCabelo        |
| CorteBarba         |
+---------+---------+
          |
          v
+-------------------+
| barbearia.exception |
| custom validations |
+-------------------+
```

## Example Terminal Output

```text
╔══════════════════════════════════════╗
║   SISTEMA DE GESTÃO DE BARBEARIA     ║
╚══════════════════════════════════════╝

----------------------------------------
  [1] Registrar Corte de Cabelo
  [2] Registrar Corte de Barba
  [3] Listar Serviços
  [4] Ver Faturamento Total
  [0] Sair
----------------------------------------
  Escolha uma opção: 1

  Nome do cliente: Ana
  Preço: R$ 35.00

✔ Serviço registrado com sucesso!
```

## Running Tests

```bash
mvn test
```

This runs the unit tests for validation, persistence, and business rules.
