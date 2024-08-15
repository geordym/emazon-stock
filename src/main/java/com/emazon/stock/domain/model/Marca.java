package com.emazon.stock.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.emazon.stock.domain.util.Constantes.LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.LONGITUD_NOMBRE_MAXIMA;

@NoArgsConstructor
@Getter
public class Marca {

    private Long id;
    private String nombre;
    private String descripcion;


    public Marca(Long id, String nombre, String descripcion) {
        this.id = id;
        setNombre(nombre); 
        setDescripcion(descripcion);  
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.length() > LONGITUD_NOMBRE_MAXIMA) {
            throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres");
        }
        this.nombre = nombre;
    }


    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (descripcion.length() > LONGITUD_DESCRIPCION_MAXIMA) {
            throw new IllegalArgumentException("La descripción no puede tener más de 120 caracteres");
        }
        this.descripcion = descripcion;
    }

}