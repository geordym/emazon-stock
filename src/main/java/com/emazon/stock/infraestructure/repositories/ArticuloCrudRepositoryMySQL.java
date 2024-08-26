package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloCrudRepositoryMySQL extends JpaRepository<ArticuloEntity, Long> {


}
