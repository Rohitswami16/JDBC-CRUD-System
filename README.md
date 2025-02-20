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

JDBC-CRUD-System/
├── JRE System Library/                  
├── src/                                 
│   ├── JDBC_CRUD_OPERATIONS/           
│   │   ├── AddEmployeeFrame.java                
│   │   ├── EmployeeDetails.java             
│   │   ├── RemoveEmployeeFrame.java             
│   │   ├── HomePage.java  
│   │   ├── UpdateEmployeeFrame.java   
│   │   ├── ViewByIdFrame.java                         
├── Referenced Libraries/                
│   └── mysql-connector-java.jar        
├── README.md                            


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

--- 

## 🚀 How to Run the Project

### 📥 Clone the Repository
To clone the repository, run the following commands in your terminal:
bash
git clone https://github.com/Rohitswami16/JDBC-CRUD-System.git
cd JDBC-CRUD-System

### 🔧 Set Up the Database
Install MySQL or any JDBC-compatible database.
Execute the SQL script in db-scripts/schema.sql to create the necessary database and table.
### 🔧 Configure Database Connection
Edit the application.properties file in the resources folder to specify your database credentials:
db.url=jdbc:mysql://localhost:3306/EmployeeDB
db.user=root
db.password=password

### ▶️ Run the Application
1. Open the project in your IDE (e.g., Eclipse or IntelliJ IDEA).
2. Add the JDBC driver (mysql-connector-java.jar) to the classpath.
3. Run the Main.java file to start the application.

---

🕹️ Functionality of Buttons
### View Employees
Displays all employees in the database with their details such as ID, name, salary, email, phone, and address.
### Add Employee
Prompts the user to enter employee details (name, position, salary, email, phone, address).
If a duplicate entry (e.g., same name or email) is detected, a message "Duplicate entry not allowed" will be displayed.
### View by ID
Prompts the user to enter an employee's ID.
If the employee exists, their details will be displayed.
If not, a message "Employee doesn't exist" will be shown.
### Remove Employee
Prompts the user to enter an employee's ID.
The corresponding record will be removed from the database.
If the employee doesn't exist, a message "Employee not found" will be displayed.
### Update Employee
Prompts the user to enter an employee's ID.
If the employee exists, their details will be displayed.
The user can then update fields like name, position, salary, email, phone, and address.

## 📸 Screenshots

HOME 

![Home](https://github.com/user-attachments/assets/1891e088-a7cb-4fcd-8083-5b84fb546136)

EMPLOYEE DETAILS

![EmployeeDetails](https://github.com/user-attachments/assets/38567982-47f5-4e86-acaa-25d1e474515c)

ADD NEW EMPLOYEE
![AddNewEmployee](https://github.com/user-attachments/assets/09bbacf5-0ab8-4bfd-ad4c-2d72b4ad5086)

VIEW BY EMPLOYEE ID

![ViewByID](https://github.com/user-attachments/assets/8b74ebec-3a6f-4584-8e5f-a249027a4c59)

UPDATE EMPLOYEE DETAILS

![UpdateEmployeeDetails](https://github.com/user-attachments/assets/a3e1672f-22db-45cc-b3e6-15aefe52824f)

REMOVE EMPLOYEE DETAILS
![RemoveEmployeeDetails](https://github.com/user-attachments/assets/4091cdc0-35b6-4a98-9018-cd5b3060b867)

## 🚀 Future Enhancements
Add role-based authentication for secure access.
Implement advanced search and filtering options.
Enhance the GUI with modern UI frameworks like JavaFX.
Support for exporting employee data to Excel or PDF.
## 📜 License
This project is open-source and available under the MIT License. Feel free to contribute to this project by submitting issues or pull requests!

## 📧 Contact
For any questions or issues, please contact:

🔗 LinkedIn: [LinkedIn](https://www.linkedin.com/in/rohit-swami16/)

🐙 GitHub: [Rohitswami16](https://github.com/Rohitswami16)

