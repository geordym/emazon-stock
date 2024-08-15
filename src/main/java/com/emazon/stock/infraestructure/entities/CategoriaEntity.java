package com.emazon.stock.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idcategoria;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public CategoriaEntity(Long idcategoria, String nombre, String descripcion) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}