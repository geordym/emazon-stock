package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.infraestructure.entities.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaCrudRepositoryMySQL extends JpaRepository<MarcaEntity, Long> {

    Optional<MarcaEntity> findByNombre(String nombre);
}
