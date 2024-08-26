package com.emazon.stock.infraestructure.entities;


import com.emazon.stock.domain.model.Category;
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

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="descripcion", nullable = true)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precio;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<CategoryArticuloEntity> categoriesArticulos;

    public List<CategoryArticuloEntity> getCategories() {
        return this.categoriesArticulos;
    }


}