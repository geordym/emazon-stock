package com.emazon.stock.infraestructure.entities;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "marcas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idMarca;

    @Column(unique = true, length = 50, nullable = false)
    private String nombre;

    @Column(length = 120, nullable = false)
    private String descripcion;

    public MarcaEntity(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
