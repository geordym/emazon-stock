package com.emazon.stock.domain.model;

import lombok.Data;

import static com.emazon.stock.domain.util.Constantes.CATEGORY_LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.CATEGORY_LONGITUD_NOMBRE_MAXIMA;

@Data
public class Category{
    private Long id_categoria;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(Long id, String name, String description) {
        this.id_categoria = id;
        this.name = name;
        this.description = description;
    }

    public Category(Long id_categoria) {
        this.id_categoria = id_categoria;
    }
}
