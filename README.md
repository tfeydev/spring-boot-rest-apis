# Spring Boot REST APIs – Learning Project

This repository contains a collection of Spring Boot REST API modules created while following the Udemy course by **Chad Darby** and **Eric Roby**.  
Each module focuses on a specific REST API concept, gradually building real-world skills in designing modern, maintainable backend systems.

---

## 🌐 Project Documentation & Learning Hub

All detailed, step-by-step notes, setup guides, and comprehensive explanations for this course—including architecture deep dives and SQL setup—are hosted publicly on my learning portfolio.

➡️ **Visit the Full Course Notes & Documentation:** [Java & Spring Boot](https://sites.google.com/view/techthordev/learning-hub/backend_development)

---

## 📦 Modules Overview

### **01-books**
A simple in-memory REST API to practice:
- Basic GET, POST, PUT, DELETE operations
- Filtering with query parameters
- Path variables
- Simple data model (`Book`)
- Custom CORS configuration
- Swagger/OpenAPI documentation

### **02-books-continued**
Extended functionality based on module 01:
- Additional endpoints
- More validation
- Additional filtering
- Better controller structure
- Preparations for future units

### **03-spring-boot-rest-security**
Introduction to Spring Security:
- Basic authentication
- Securing endpoints
- Custom security configuration
- Working with roles / authorities

### **04-todos**
A practical mini-application:
- CRUD operations for Todo items
- Validation
- Error handling
- Simple service layer architecture
- Preparing the project for future persistence (JPA or database integration)

---

## 🚀 Technologies Used

- **Java 21+**
- **Spring Boot 4.x**
- **Spring Web MVC**
- **Spring Validation**
- **Spring Security**
- **Springdoc OpenAPI / Swagger UI**
- **Maven Multi-Module Build**

---

## ▶️ Running a module

To run a specific module, use:

```bash
mvn -pl 01-books spring-boot:run
```

Replace 01-books with any other module name:

>02-books-continued
>
>03-spring-boot-rest-security
>
>04-todos

## 📚 About this Repository

This repository is intended as a structured learning project.
Each module focuses on mastering core Spring Boot REST concepts step by step, following the patterns and best practices taught in the course.

## 📝 License

This project is released under the MIT License.
See the LICENSE file for full details.
