package com.emazon.stock.infraestructure.repositories;


import com.emazon.stock.infraestructure.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaCrudRepositoryMySQL extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNombre(String nombre);


}
