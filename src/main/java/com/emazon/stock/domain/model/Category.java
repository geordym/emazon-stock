package com.emazon.stock.domain.model;

import lombok.Data;

import static com.emazon.stock.domain.util.Constantes.CATEGORY_LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.CATEGORY_LONGITUD_NOMBRE_MAXIMA;

@Data
public class Category{
    private Long id_categoria;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        if (name == null || name.isEmpty() || name.length() > CATEGORY_LONGITUD_NOMBRE_MAXIMA) {
            throw new IllegalArgumentException("El nombre debe tener un máximo de 50 caracteres y no puede ser vacío.");
        }
        if (description == null || description.isEmpty() || description.length() > CATEGORY_LONGITUD_DESCRIPCION_MAXIMA) {
            throw new IllegalArgumentException("La descripción es obligatoria y debe tener un máximo de 90 caracteres.");
        }

        this.id_categoria = id;
        this.name = name;
        this.description = description;
    }

    public Category(Long id_categoria) {
        this.id_categoria = id_categoria;
    }
}
