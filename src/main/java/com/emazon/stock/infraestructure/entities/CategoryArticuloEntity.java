package com.emazon.stock.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "articulo_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryArticuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo_categoria")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_articulo", nullable = false)
    private ArticuloEntity articulo;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity category;

    public CategoryArticuloEntity(ArticuloEntity articulo, CategoryEntity category) {
        this.articulo = articulo;
        this.category = category;
    }


}