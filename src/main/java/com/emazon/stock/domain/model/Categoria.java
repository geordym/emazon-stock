package com.emazon.stock.domain.model;


import lombok.Data;

import java.util.List;

import static com.emazon.stock.domain.util.Constantes.LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.LONGITUD_NOMBRE_MAXIMA;

@Data
public class Categoria {

    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private List<Articulo> articulos;


    public Categoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria(Long id, String nombre, String descripcion) {
        if (nombre == null || nombre.isEmpty() || nombre.length() > LONGITUD_NOMBRE_MAXIMA) {
            throw new IllegalArgumentException("El nombre debe tener un máximo de 50 caracteres y no puede ser vacío.");
        }
        if (descripcion == null || descripcion.isEmpty() || descripcion.length() > LONGITUD_DESCRIPCION_MAXIMA) {
            throw new IllegalArgumentException("La descripción es obligatoria y debe tener un máximo de 90 caracteres.");
        }

        this.idCategoria = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
