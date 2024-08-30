package com.emazon.stock.infraestructure.entities;




import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "marcas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idMarca;

    @Column(name="nombre", unique = true, length = 50)
    private String nombre;

    @Column(name="descripcion", length = 120)
    private String descripcion;

    @OneToMany(mappedBy = "marca")
    private List<ArticuloEntity> articulos;


    public MarcaEntity(Long idMarca, String nombre, String descripcion) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public MarcaEntity(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


}
