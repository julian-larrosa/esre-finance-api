# ESRE - Sistema de Gestión de Economía Doméstica

API REST para gestión de finanzas personales desarrollada con Spring Boot 3.5.

## Características

- **Autenticación Segura:** JWT con Spring Security 6
- **Gestión de Categorías:** Crear categorías personalizadas
- **Registro de Movimientos:** Ingresos y gastos con paginación
- **Aislamiento de Datos:** Cada usuario ve solo sus datos
- **Arquitectura Escalable:** Preparada para nuevos módulos
- **Documentación API:** OpenAPI/Swagger incluido

## Stack Tecnológico

### Backend
- **Java 17**
- **Spring Boot 3.5**
- **Spring Security 6** con JWT
- **Spring Data JPA + Hibernate**
- **PostgreSQL**
- **Flyway** (migraciones)
- **MapStruct** (mapeo DTO)
- **Lombok**
- **Jakarta Validation**

### Documentación
- **OpenAPI 3.0**
- **Swagger UI**

## Cómo ejecutar

### Requisitos
- Java 17+
- PostgreSQL 12+
- Maven 3.8+

### Setup

```bash
# Clonar repositorio
git clone https://github.com/julidev34/esre.git
cd esre

# Configurar base de datos
# Editar src/main/resources/application.yml

# Compilar
mvn clean install

# Ejecutar
mvn spring-boot:run

# La API estará disponible en http://localhost:8080
# Swagger UI en http://localhost:8080/swagger-ui.html
```

## Documentación de API

### Autenticación

```bash
# Registro
POST /api/auth/register
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "password123",
  "firstName": "Juan",
  "lastName": "Pérez"
}

# Login
POST /api/auth/login
{
  "email": "usuario@example.com",
  "password": "password123"
}

# Respuesta (con JWT token)
{
  "token": "eyJhbGc...",
  "type": "Bearer"
}
```

### Categorías

```bash
# Crear categoría
POST /api/categories
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Comida",
  "description": "Gastos en comida"
}

# Listar categorías
GET /api/categories?page=0&size=10
Authorization: Bearer <token>
```

### Movimientos

```bash
# Registrar movimiento
POST /api/movements
Authorization: Bearer <token>
{
  "description": "Compra en supermercado",
  "amount": 50.00,
  "categoryId": 1,
  "type": "EXPENSE", // EXPENSE o INCOME
  "date": "2025-07-15"
}

# Listar movimientos
GET /api/movements?page=0&size=20&sort=date,desc

## Autor

Julián Larrosa 
Authorization: Bearer <token>
```
