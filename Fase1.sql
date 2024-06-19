CREATE DATABASE Almacen;
USE Almacen;

CREATE TABLE Productos(
idProducto INT PRIMARY KEY AUTO_INCREMENT,
descripcion VARCHAR(50),
stock INT,
precioVenta DECIMAL
);

CREATE TABLE Clientes(
idCliente INT PRIMARY KEY AUTO_INCREMENT,
dni VARCHAR(8),
nombre VARCHAR(50),
apellido VARCHAR(50),
email VARCHAR(50),
telefono VARCHAR(13)
);












