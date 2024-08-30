package com.emazon.stock.infraestructure.entities;


import com.emazon.stock.domain.model.Category;
import com.github.javafaker.Cat;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articulos")
public class ArticuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="descripcion", nullable = true)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precio;

    @ManyToMany
    @JoinTable(
            name = "articulo_categorias",
            joinColumns = @JoinColumn(name = "id_articulo"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<CategoryEntity> categories;



    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcaEntity marca;



}