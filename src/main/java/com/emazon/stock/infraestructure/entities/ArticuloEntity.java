package com.emazon.stock.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articulos")
public class ArticuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = true)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precio;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<CategoriaArticuloEntity> categoriasArticulos;

    public List<CategoriaArticuloEntity> getCategorias() {
        return this.categoriasArticulos;
    }
}