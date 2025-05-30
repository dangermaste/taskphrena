CREATE DATABASE IF NOT EXISTS taskphrena;
USE taskphrena;

-- Tabla de usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

-- Tabla de tareas
CREATE TABLE tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreTarea VARCHAR(255) NOT NULL,
    fechaCreacion DATETIME,
    fechaLimite DATETIME,
    descripcion TEXT,
    tipoTarea VARCHAR(255) NOT NULL,
    completado BOOLEAN DEFAULT FALSE,
    usuario_id INT NOT NULL
);

INSERT INTO usuarios (nombre, email, contrasena) 
VALUES ('kad', 'test@test.es', '1122');