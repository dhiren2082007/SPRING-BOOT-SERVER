# 🍔 Swiggy Clone – Microservices Project

A **Swiggy-inspired Food Delivery Platform** built using **Spring Boot Microservices**. The project consists of three independent services that communicate with each other using **REST APIs** via **RestTemplate**.

Each microservice has its own independent H2 database, following the **Database per Service** pattern.

---

# 📌 Architecture

```text
                    REST (RestTemplate)
+--------------------------------------------------------------+

      +-------------------------+
      |      Swiggy Service     |
      |       Port : 8080       |
      |-------------------------|
      | Users                   |
      | Menu Items              |
      | Orders                  |
      | Payments                |
      +-----------+-------------+
                  ^
                  |
                  |
      +-----------+-------------+
      | Restaurant Management   |
      |      Port : 8083        |
      |-------------------------|
      | Restaurants             |
      | Calls Swiggy APIs       |
      +-----------+-------------+
                  ^
                  |
                  |
      +-----------+-------------+
      | Delivery Management     |
      |      Port : 8084        |
      |-------------------------|
      | Delivery Agents         |
      | Deliveries              |
      | Calls Restaurant APIs   |
      +-------------------------+
```

---

# 📖 Project Overview

The project is divided into three independent Spring Boot applications.

## 1. Swiggy Service (Port: 8080)

Responsible for:

- User Management
- Menu Item Management
- Order Management
- Payment Management

---

## 2. Restaurant Management Service (Port: 8083)

Responsible for:

- Restaurant Management
- Cuisine Filtering
- Active Restaurant Status
- Fetching Menu Items from Swiggy Service using RestTemplate

---

## 3. Delivery Management Service (Port: 8084)

Responsible for:

- Delivery Agent Management
- Delivery Assignment
- Delivery Tracking
- Restaurant Validation using RestTemplate before assigning deliveries

---

# 🚀 Tech Stack

- Java 26
- Spring Boot 4.1.0
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Lombok
- Maven
- RestTemplate

---

# 📂 Microservices

| Service | Port | Description |
|----------|------|-------------|
| Swiggy | 8080 | Users, Items, Orders, Payments |
| Restaurant Management | 8083 | Restaurants & External Item APIs |
| Delivery Management | 8084 | Delivery Agents & Deliveries |

---

# 🗄 Database

Each service owns its own H2 in-memory database.

| Service | Database |
|----------|----------|
| Swiggy | H2 |
| Restaurant Management | H2 |
| Delivery Management | H2 |

No database is shared between services.

---

# ▶ Running the Project

Open three terminals.

## Terminal 1

```bash
cd swiggy
./mvnw spring-boot:run
```

Runs on

```
http://localhost:8080
```

---

## Terminal 2

```bash
cd restaurant-management-service
mvn spring-boot:run
```

Runs on

```
http://localhost:8083
```

---

## Terminal 3

```bash
cd delivery-management-service
mvn spring-boot:run
```

Runs on

```
http://localhost:8084
```

---

# 💾 H2 Console

| Service | URL |
|----------|-----|
| Swiggy | http://localhost:8080/h2-console |
| Restaurant Management | http://localhost:8083/h2-console |
| Delivery Management | http://localhost:8084/h2-console |

---

# 📌 API Reference

---

# 🍽 Swiggy Service

Base URL

```
http://localhost:8080
```

## Users

| Method | Endpoint |
|--------|----------|
| POST | /api/users |
| GET | /api/users |
| GET | /api/users/{id} |
| PUT | /api/users/{id} |
| DELETE | /api/users/{id} |

---

## Items

| Method | Endpoint |
|--------|----------|
| POST | /api/items |
| GET | /api/items |
| GET | /api/items/{id} |
| PUT | /api/items/{id} |
| PUT | /api/items/{id}/toggle-availability |
| DELETE | /api/items/{id} |

---

## Orders

| Method | Endpoint |
|--------|----------|
| POST | /api/orders |
| GET | /api/orders |
| GET | /api/orders/{id} |
| PUT | /api/orders/{id}/status |
| PUT | /api/orders/{id}/cancel |

---

## Payments

| Method | Endpoint |
|--------|----------|
| POST | /api/payments |
| GET | /api/payments |
| PUT | /api/payments/{id}/status |

---

# 🍴 Restaurant Management Service

Base URL

```
http://localhost:8083
```

## Restaurants

| Method | Endpoint |
|--------|----------|
| POST | /api/restaurants |
| GET | /api/restaurants |
| GET | /api/restaurants/{id} |
| GET | /api/restaurants/{id}/active |
| GET | /api/restaurants/cuisine/{cuisineType} |
| PUT | /api/restaurants/{id} |
| DELETE | /api/restaurants/{id} |

---

## External APIs

| Method | Endpoint |
|--------|----------|
| GET | /api/external/items |
| GET | /api/external/items/{id} |
| GET | /api/external/items/available-count |

These endpoints fetch data from the Swiggy service using RestTemplate.

---

# 🚚 Delivery Management Service

Base URL

```
http://localhost:8084
```

## Delivery Agents

| Method | Endpoint |
|--------|----------|
| POST | /api/delivery-agents |
| GET | /api/delivery-agents |
| GET | /api/delivery-agents/available |
| GET | /api/delivery-agents/{id} |
| PUT | /api/delivery-agents/{id} |
| PUT | /api/delivery-agents/{id}/availability |
| DELETE | /api/delivery-agents/{id} |

---

## Deliveries

| Method | Endpoint |
|--------|----------|
| POST | /api/deliveries |
| GET | /api/deliveries |
| GET | /api/deliveries/{id} |
| PUT | /api/deliveries/{id}/status |

---

## External APIs

| Method | Endpoint |
|--------|----------|
| GET | /api/external/restaurants/{id}/active |

These endpoints call Restaurant Management Service using RestTemplate.

---

# 🔄 RestTemplate Communication

## Restaurant Management ➜ Swiggy

```
Restaurant Service
        │
        │ RestTemplate
        ▼
Swiggy Service

GET /api/items
GET /api/items/{id}
```

---

## Delivery Management ➜ Restaurant Management

```
Delivery Service
        │
        │ RestTemplate
        ▼
Restaurant Service

GET /api/restaurants/{id}/active
```

---

# 📁 Project Structure

```
src/main/java
│
├── config
├── controller
├── dto
├── exception
├── model
├── repository
└── service
    ├── interface
    └── implementation
```

---

# 📦 Features

- Microservices Architecture
- RESTful APIs
- Independent Databases
- Spring Data JPA
- Bean Validation
- Global Exception Handling
- RestTemplate Communication
- CRUD Operations
- H2 Database
- Maven Project
- Layered Architecture

---

# ⚠ Known Limitations

- Restaurant cannot be reactivated once deactivated.
- Individual deliveries cannot be deleted.
- H2 databases are in-memory and reset after application restart.
- No authentication or authorization implemented.
- Synchronous communication using RestTemplate only.

---

# 👨‍💻 Author

**Dhiren D**

Spring Boot Microservices Project – Swiggy Clone
