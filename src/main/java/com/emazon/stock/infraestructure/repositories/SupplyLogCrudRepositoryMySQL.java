package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.infraestructure.entities.SupplyLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SupplyLogCrudRepositoryMySQL extends JpaRepository<SupplyLogEntity, Long> {

    @Query("SELECT s FROM SupplyLogEntity s WHERE s.supplyId = :supplyId")
    Optional<SupplyLogEntity> findSupplyLogBySupplyId(@Param("supplyId") Long supplyId);

}
