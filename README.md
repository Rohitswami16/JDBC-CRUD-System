# JDBC-CRUD-System

## 📜 Project Overview

The **JDBC-CRUD-System** is a Java-based desktop application that demonstrates the implementation of CRUD (Create, Read, Update, Delete) operations using **JDBC** (Java Database Connectivity). This system is designed for managing employee records and includes a user-friendly **GUI** built with **JFrame**. The application allows users to perform operations such as viewing, adding, updating, and removing employee records.

---

## ✨ Key Features

### Home Page
- The central interface with five buttons to navigate through the system:
  - **View Employees**: Displays all employee records, including employee ID, name, salary, email, phone number, and address.
  - **View by ID**: Allows the user to search for and display a specific employee's details by their ID.
  - **Add Employee**: Allows adding new employee records into the system with validation to prevent duplicate entries.
  - **Remove Employee**: Removes an employee record using their ID.
  - **Update Employee**: Updates details (such as name, salary, email, etc.) of an existing employee.
  
### Exception Handling
- Provides meaningful messages when operations fail (e.g., "No such employee present," "Duplicate entry not allowed").

---

## 🛠️ Technologies Used

- **Programming Language**: Java
- **Database**: MySQL (or any JDBC-compatible database)
- **GUI Framework**: JFrame
- **JDBC**: For database connectivity and operations

---

## 📂 Project Structure


sql
Copy code

---

## 📄 Database Schema

To set up the database, execute the following SQL script:

```sql
CREATE DATABASE EmployeeDB;
USE EmployeeDB;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    position VARCHAR(50),
    salary DECIMAL(10, 2),
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(200)
);
```
# JDBC-CRUD-System

## 🚀 How to Run the Project

### 📥 Clone the Repository
To clone the repository, run the following commands in your terminal:
bash
git clone https://github.com/Rohitswami16/JDBC-CRUD-System.git
cd JDBC-CRUD-System
