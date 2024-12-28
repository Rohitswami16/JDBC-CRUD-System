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
🚀 How to Run the Project
1. 📥 Clone the Repository
bash
Copy code
git clone https://github.com/Rohitswami16/JDBC-CRUD-System.git
cd JDBC-CRUD-System
2. 🔧 Set Up the Database
Install MySQL or any JDBC-compatible database.
Execute the SQL script in db-scripts/schema.sql to create the necessary database and table.
3. 🔧 Configure Database Connection
Edit the application.properties file in the resources folder to specify your database credentials:
properties
Copy code
db.url=jdbc:mysql://localhost:3306/EmployeeDB
db.user=root
db.password=password
4. ▶️ Run the Application
Open the project in your IDE (e.g., Eclipse or IntelliJ IDEA).
Add the JDBC driver (mysql-connector-java.jar) to the classpath.
Run the Main.java file to start the application.
🕹️ Functionality of Buttons
View Employees: Displays all employees in the database with their details such as ID, name, salary, email, phone, and address.

Add Employee: Prompts the user to enter employee details (name, position, salary, email, phone, address). If a duplicate entry (e.g., same name or email) is detected, it will display a message: "Duplicate entry not allowed."

View by ID: Prompts the user to enter an employee's ID. If the employee exists, their details are displayed; otherwise, a message "Employee doesn't exist" is shown.

Remove Employee: Prompts the user to enter an employee's ID. The corresponding record is removed from the database. If the employee doesn't exist, a message "Employee not found" is shown.

Update Employee: Prompts the user to enter an employee's ID. If the employee exists, their details are displayed. The user can then update fields like name, position, salary, email, phone, and address.

📸 Screenshots
(Add screenshots of your application's GUI here to provide a visual overview of how the system works.)

🚀 Future Enhancements
Add role-based authentication for secure access.
Implement advanced search and filtering options.
Enhance the GUI with modern UI frameworks like JavaFX.
Support for exporting employee data to Excel or PDF.
📜 License
This project is open-source and available under the MIT License.

Feel free to contribute to this project by submitting issues or pull requests!

📧 Contact
For any questions or issues, please contact:

🔗 LinkedIn: LinkedIn
🐙 GitHub: Rohitswami16
