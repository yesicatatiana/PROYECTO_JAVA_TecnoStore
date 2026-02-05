
CREATE DATABASE TecnoStore; 


CREATE TABLE celular (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100),
    sistema_operativo DATE,
    gama VARCHAR(100) NOT NULL,
    precio VARCHAR(100) NOT NULL,
    stock VARCHAR(100) NOT NULL,
);
/* validar que precio y stock sean positivos*/


CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(100) UNIQUE,
    correo VARCHAR(100) UNIQUE,
    telefono VARCHAR(100) NOT NULL,
);


CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente VARCHAR(100) NOT NULL,
    fecha DATE,
    total INT,
);

CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta VARCHAR(100) NOT NULL,
    id_celular VARCHAR(100) UNIQUE,
    cantidad DATE,
    subtotal VARCHAR(100) NOT NULL,
);


CREATE TABLE marca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
);


