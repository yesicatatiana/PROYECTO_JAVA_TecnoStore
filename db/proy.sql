
CREATE DATABASE TecnoStore; 
use TecnoStore;


CREATE TABLE celular (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50),
    sistema_operativo VARCHAR(50),
    gama ENUM('Baja', 'Media', 'Alta') NOT NULL,
    precio INT NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    identificacion VARCHAR(50) UNIQUE,
    correo VARCHAR(50) UNIQUE,
    telefono VARCHAR(50) NOT NULL
);


CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total double NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);


CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad double NOT NULL,
    subtotal double NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES venta(id),
    FOREIGN KEY (id_celular) REFERENCES celular(id)
);
