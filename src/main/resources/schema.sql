CREATE TABLE IF NOT EXISTS usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tarea (
    id_tarea BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    descripcion VARCHAR(255),
    usuario BIGINT
);

CREATE TABLE IF NOT EXISTS estado_tarea (
    id_estado_tarea BIGINT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(255),
    estado_activo boolean,
    tarea BIGINT
);