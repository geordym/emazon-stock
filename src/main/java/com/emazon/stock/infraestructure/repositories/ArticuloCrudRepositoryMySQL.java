package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticuloCrudRepositoryMySQL extends JpaRepository<ArticuloEntity, Long> {

    @Query("SELECT a FROM ArticuloEntity a " +
            "JOIN FETCH a.categories " +
            "LEFT JOIN FETCH a.marca " +
            "WHERE a.idArticulo = :id")
    ArticuloEntity findByIdWithCategoriesAndMark(@Param("id") Long id);

}
