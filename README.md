# Supply Chain Dashboard

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![License](https://img.shields.io/badge/license-MIT-blue)]()
[![Java](https://img.shields.io/badge/java-17-orange)]()
[![Spring Boot](https://img.shields.io/badge/springboot-3.x-green)]()

A full-stack **Supply Chain Dashboard** to manage inventory, sales, and stock alerts for small-to-medium businesses.  
Built with **Java Spring Boot, PostgreSQL, and React**.

---

## Features (MVP)
- CRUD operations for Products, Inventory, and Sales Records
- Low-stock alerts on inventory
- Simple React frontend with inventory & sales views
- PostgreSQL database for persistent storage
- Dockerized setup for local development

---

## Tech Stack
- **Backend**: Java, Spring Boot, Spring Data JPA, Lombok
- **Frontend**: React, Axios, Material-UI / Chakra-UI
- **Database**: PostgreSQL
- **DevOps**: Docker, GitHub Actions (CI/CD)
- **Testing**: JUnit, Mockito

---

## Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/dkapil_97/supplychain-dashboard.git
cd supplychain-dashboard

### 2. Start MySql and configure in application properties file as supply_chain

### 3. Run Backend using 
    ./mvnw spring-boot:run

