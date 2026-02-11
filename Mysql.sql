drop database tecnostore_db;
CREATE DATABASE IF NOT EXISTS tecnostore_db;
USE tecnostore_db;

CREATE TABLE marcas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    correo VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    stock INT NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    sistema_operativo VARCHAR(50) NOT NULL,
    gama ENUM('ALTA','MEDIA','BAJA') NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_marca INT NOT NULL,
    FOREIGN KEY (id_marca) REFERENCES marcas(id)
);

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subtotal_Iva DECIMAL(10,2) NOT NULL,
    subtotal_sin_Iva DECIMAL(10,2) NOT NULL,
    id_cliente INT NOT NULL,
    fecha_venta VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    id_celular INT NOT NULL,
    id_venta INT NOT NULL,
    FOREIGN KEY (id_celular) REFERENCES celulares(id),
    FOREIGN KEY (id_venta) REFERENCES ventas(id)
);

show tables;

select * from marcas;