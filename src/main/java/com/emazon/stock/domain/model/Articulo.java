package com.emazon.stock.domain.model;


import lombok.Data;

import java.util.List;

@Data
public class Articulo {

    private Long idArticulo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
    private List<CategoryArticulo> categories;



    public Articulo(Long idArticulo, String nombre, String descripcion, int cantidad, double precio, List<CategoryArticulo> categorias) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categories = categorias;
    }

    public Articulo(String nombre, String descripcion, int cantidad, double precio, List<CategoryArticulo> categories) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categories = categories;
    }
}