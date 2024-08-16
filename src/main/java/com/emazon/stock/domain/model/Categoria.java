package com.emazon.stock.domain.model;


import lombok.Data;

import static com.emazon.stock.domain.util.Constantes.LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.LONGITUD_NOMBRE_MAXIMA;

@Data
public class Categoria {
    private Long id_categoria;
    private String nombre;
    private String descripcion;

    public Categoria(Long id, String nombre, String descripcion) {
        if (nombre == null || nombre.isEmpty() || nombre.length() > LONGITUD_NOMBRE_MAXIMA) {
            throw new IllegalArgumentException("El nombre debe tener un máximo de 50 caracteres y no puede ser vacío.");
        }
        if (descripcion == null || descripcion.isEmpty() || descripcion.length() > LONGITUD_DESCRIPCION_MAXIMA) {
            throw new IllegalArgumentException("La descripción es obligatoria y debe tener un máximo de 90 caracteres.");
        }

        this.id_categoria = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
