package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.infraestructure.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryCrudRepositoryMySQL extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String nombre);

}
