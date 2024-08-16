package com.emazon.stock.infraestructure.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articulo_categoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaArticuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo_categoria")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_articulo", nullable = false)
    private ArticuloEntity articulo;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    public CategoriaArticuloEntity(ArticuloEntity articulo, CategoriaEntity categoria) {
        this.articulo = articulo;
        this.categoria = categoria;
    }
}