# 📝 Task Manager Console App

A console-based Task Manager application developed in Java that allows users to register, log in, and manage their personal tasks with different priorities, descriptions, and deadlines.

## 🚀 Features

- 🧑 User Registration and Login (with MySQL persistence)
- ✅ Add, List, Edit, and Delete Tasks
- 🔐 Password-protected sessions
- 🔍 Task filtering by user
- 🎨 Colored console interface using ANSI escape codes

## 💻 Technologies Used

- **Java 17+**
- **MySQL** (via **XAMPP**)
- **JDBC** for database connection
- **Maven** *(optional if you plan to manage dependencies)*
- **XAMPP** for local MySQL server

## 📂 Project Structure
src/
├── Colores.java # ANSI color constants for terminal output
├── Conexion.java # Handles MySQL connection
├── Usuario.java # User registration and login logic
├── Tarea.java # Task class with task logic (CRUD)
├── Guardar.java # Persistence layer for saving/loading tasks
├── TipoTarea.java # Enum for task priority (ALTA, MEDIA, BAJA)
└── Main.java # Entry point of the application
