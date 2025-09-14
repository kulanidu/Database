# Student Database Management System (Console-based Java + JDBC)

## 👨‍💻 Author

-   **Name:** Kulanidu Jayadew Kumarage\
-   **Index Number:** S16856\
-   **Registration Number:** 2022S19662

------------------------------------------------------------------------

## 📌 Project Overview

This is a **console-based Java application** built using **JDBC** to
manage student records in a MySQL database.\
The application supports all basic **CRUD operations**: - Create the
student table - Insert new students - View all student records - Update
student details - Delete student records

The program is menu-driven and allows users to interact with the
database directly from the terminal.

------------------------------------------------------------------------

## ⚙️ Setup Instructions

### 1. Install Requirements

-   **Java JDK 8+**
-   **MySQL Server (5.7+ / 8.0+)**
-   **MySQL Connector/J** (JDBC driver)

### 2. Create the Database

Login to MySQL and create the required database:

``` sql
CREATE DATABASE stu_database;
```

### 3. Configure Database Connection

The application uses the following default credentials: - **Database:**
stu_database\
- **Username:** root\
- **Password:** root\
- **Host:** localhost\
- **Port:** 3306

*(Update inside the code if different credentials are required.)*

### 4. Compile the Program

Place the MySQL Connector `.jar` file in your project folder. Then run:

``` bash
javac StudentDatabaseApp.java
```

### 5. Run the Program

``` bash
java -cp .:mysql-connector-j-9.0.0.jar StudentDatabaseApp
```

> Replace `mysql-connector-j-9.0.0.jar` with your actual JDBC connector
> version.

------------------------------------------------------------------------

## 📂 Application Menu

When running, the program shows this menu:

    ===== Student Database Menu =====
    1. Create Students Table
    2. Add Student
    3. View Students
    4. Update Student
    5. Delete Student
    6. Exit

------------------------------------------------------------------------

## 📌 Assumptions & Notes

-   The **students** table will be created automatically if it does not
    already exist.\
-   Application assumes correct **Java & MySQL setup** in your
    environment.\
-   Tested with **MySQL 8.0** and **Java 17**.\
-   Uses `PreparedStatement` for safe and secure database operations.\
-   User is expected to provide valid inputs in the console.

------------------------------------------------------------------------

## ✅ Example Run

    ✅ Connected to Database Successfully!

    ===== Student Database Menu =====
    1. Create Students Table
    2. Add Student
    3. View Students
    4. Update Student
    5. Delete Student
    6. Exit
    Choose an option: 2
    Enter Name: Alex
    Enter Age: 21
    Enter Course: Computer Science
    ✅ Student added successfully!

------------------------------------------------------------------------

## 🙏 Acknowledgments

This project was developed as part of learning **Advanced Programming
Techniques -- JDBC**.
