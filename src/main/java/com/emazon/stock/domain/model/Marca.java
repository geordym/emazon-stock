package com.emazon.stock.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.emazon.stock.domain.util.Constantes.MARCA_LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.MARCA_LONGITUD_NOMBRE_MAXIMA;


@NoArgsConstructor
@Getter
public class Marca {

    private Long id;
    private String nombre;
    private String descripcion;

    public Marca(Long id) {
        this.id = id;
    }

    public Marca(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Marca(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void setId(Long id) {
        this.id = id;
    }






}