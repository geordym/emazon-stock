package com.emazon.stock.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Articulo {

    private Long idArticulo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
    private List<Category> categories;
    private Marca marca;


    public Articulo(String nombre, String descripcion, int cantidad, double precio, List<Category> categories, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categories = categories;
        this.marca = marca;
    }

    public Articulo(Long idArticulo, String nombre, String descripcion, int cantidad, double precio, List<Category> categorias, Marca marca) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categories = categorias;
        this.marca = marca;
    }

    public Articulo(String nombre, String descripcion, int cantidad, double precio, List<Category> categories) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categories = categories;
    }
}