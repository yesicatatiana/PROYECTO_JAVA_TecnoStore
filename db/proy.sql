
CREATE DATABASE TecnoStore; 
use TecnoStore;


CREATE TABLE celular (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50),
    sistema_operativo VARCHAR(50),
    gama ENUM('Baja', 'Media', 'Alta') NOT NULL,
    precio INT,
    stock VARCHAR(50) NOT NULL,
);
/* validar que precio y stock sean positivos*/


CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    identificacion VARCHAR(50) UNIQUE,
    correo VARCHAR(50) UNIQUE,
    telefono VARCHAR(50) NOT NULL,
);


CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    fecha DATE,
    total INT,
);

CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta, foreing key,
    id_celular INT,
    cantidad VARCHAR(100),
    subtotal VARCHAR(100) NOT NULL,
);



