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
    private List<CategoriaArticulo> categorias;



    public Articulo(Long idArticulo, String nombre, String descripcion, int cantidad, double precio, List<CategoriaArticulo> categorias) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categorias = categorias;
    }

    public Articulo(String nombre, String descripcion, int cantidad, double precio, List<CategoriaArticulo> categorias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categorias = categorias;
    }
}