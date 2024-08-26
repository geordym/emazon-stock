CREATE TABLE articulos (
    id_articulo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    precio DECIMAL(10, 2) NOT NULL
);

-- Crear la tabla de relación entre artículos y categorías
CREATE TABLE articulo_category (
    id_articulo_categoria BIGINT NOT NULL AUTO_INCREMENT,
    id_articulo BIGINT NOT NULL,
    id_category BIGINT NOT NULL,
    PRIMARY KEY (id_articulo_categoria),
    FOREIGN KEY (id_articulo) REFERENCES articulos(id_articulo) ON DELETE CASCADE,
    FOREIGN KEY (id_category) REFERENCES categories(id_category) ON DELETE CASCADE
);
