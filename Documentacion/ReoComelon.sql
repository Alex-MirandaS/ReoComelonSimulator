-- ===========================================================
-- BASE DE DATOS: Reo_Comelon
-- Descripción: Modelo relacional según el diagrama proporcionado
-- Autor: William Alexander Miranda Santos
-- ===========================================================

CREATE DATABASE IF NOT EXISTS Reo_Comelon;
USE Reo_Comelon;

-- ==============================
-- Tabla: tipo_preso
-- ==============================
CREATE TABLE tipo_preso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipoPreso VARCHAR(100) NOT NULL
);

-- ==============================
-- Tabla: tipo_receta
-- ==============================
CREATE TABLE tipo_receta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_receta VARCHAR(100) NOT NULL
);

-- ==============================
-- Tabla: ingrediente
-- ==============================
CREATE TABLE ingrediente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ingrediente VARCHAR(100) NOT NULL,
    vida_util_dias INT NOT NULL,
    cantidad INT NOT NULL
);

-- ==============================
-- Tabla: registro_bodega
-- ==============================
CREATE TABLE registro_bodega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bodega VARCHAR(100) NOT NULL,
    capacidad DOUBLE NOT NULL
);

-- ==============================
-- Tabla: registro_presos
-- ==============================
CREATE TABLE registro_presos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_preso INT NOT NULL,
    cantidad INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_tipo_preso) REFERENCES tipo_preso(id)
);

-- ==============================
-- Tabla: bitacora_registro_presos
-- ==============================
CREATE TABLE bitacora_registro_presos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_registro_presos INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    es_insert BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_registro_presos) REFERENCES registro_presos(id)
);

-- ==============================
-- Tabla: ocupacion_caja
-- ==============================
CREATE TABLE ocupacion_caja (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ocupacion INT NOT NULL, -- ocupación en m³
    activo BOOLEAN DEFAULT TRUE
);

-- ==============================
-- Tabla: bitacora_registro_ocupacion_caja
-- ==============================
CREATE TABLE bitacora_registro_ocupacion_caja (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_ocupacion_caja INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    es_insert BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_ocupacion_caja) REFERENCES ocupacion_caja(id)
);

-- ==============================
-- Tabla: caja_ingredientes
-- ==============================
CREATE TABLE caja_ingredientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_ocupacion_caja INT NOT NULL,
    cantidad INT NOT NULL,
    precio DOUBLE NOT NULL,
    id_ingrediente INT NOT NULL,
    FOREIGN KEY (id_ocupacion_caja) REFERENCES ocupacion_caja(id),
    FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id)
);

-- ==============================
-- Tabla: receta
-- ==============================
CREATE TABLE receta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_Tipo_Receta INT NOT NULL,
    es_premium BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_Tipo_Receta) REFERENCES tipo_receta(id)
);

-- ==============================
-- Tabla: receta_ingrediente
-- ==============================
CREATE TABLE receta_ingrediente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_receta INT NOT NULL,
    id_ingrediente INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (id_receta) REFERENCES receta(id),
    FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id)
);

-- ==============================
-- Tabla: menu
-- ==============================
CREATE TABLE menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_preso INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (id_tipo_preso) REFERENCES tipo_preso(id)
);

-- ==============================
-- Tabla: detalle_menu
-- ==============================
CREATE TABLE detalle_menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_menu INT NOT NULL,
    id_receta_des INT,
    id_receta_lunch INT,
    id_receta_cena INT,
    FOREIGN KEY (id_menu) REFERENCES menu(id),
    FOREIGN KEY (id_receta_des) REFERENCES receta(id),
    FOREIGN KEY (id_receta_lunch) REFERENCES receta(id),
    FOREIGN KEY (id_receta_cena) REFERENCES receta(id)
);

-- ==============================
-- Tabla: registro_simulacion
-- ==============================
CREATE TABLE registro_simulacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_registro_presos INT NOT NULL,
    id_bodega INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    dias INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    es_premium BOOLEAN DEFAULT FALSE,
    presupuesto DOUBLE,
    perdida DOUBLE,
    FOREIGN KEY (id_registro_presos) REFERENCES registro_presos(id),
    FOREIGN KEY (id_bodega) REFERENCES registro_bodega(id)
);

-- ==============================
-- Tabla: simulacion_menus
-- ==============================
CREATE TABLE simulacion_menus (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_simulacion INT NOT NULL,
    id_menu INT NOT NULL,
    FOREIGN KEY (id_simulacion) REFERENCES registro_simulacion(id),
    FOREIGN KEY (id_menu) REFERENCES menu(id)
);

