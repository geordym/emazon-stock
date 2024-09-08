package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.application.mapper.SupplyMapper;
import com.emazon.stock.domain.model.SupplyLog;
import com.emazon.stock.domain.puertos.out.SupplyLogPersistencePort;
import com.emazon.stock.infraestructure.entities.SupplyLogEntity;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.repositories.SupplyLogCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class SupplyLogPersistenceMySQLAdapter implements SupplyLogPersistencePort {

    private final SupplyLogCrudRepositoryMySQL supplyLogCrudRepositoryMySQL;
    @Override
    public Optional<SupplyLog> findSupplyLogBySupplyId(Long supplyId) {
        Optional<SupplyLogEntity> supplyLogEntity = supplyLogCrudRepositoryMySQL.findSupplyLogBySupplyId(supplyId);
        if(supplyLogEntity.isEmpty()){
            return Optional.empty();
        }

        SupplyLog supplyLogModel = SupplyMapper.entityToDomain(supplyLogEntity.get());
        return Optional.of(supplyLogModel);
    }

    @Override
    public void createSupplyLog(SupplyLog supplyLog) {
        SupplyLogEntity supplyLogEntity = new SupplyLogEntity();
        supplyLogEntity.setSupplyId(supplyLog.getSupplyId());
        supplyLogEntity.setArticle(ArticuloMapper.domainToEntity(supplyLog.getArticle()));
        supplyLogEntity.setStatus(supplyLog.getStatus());
        supplyLogEntity.setReceivedAt(supplyLog.getReceivedAt());
        supplyLogEntity.setConfirmedAt(supplyLog.getConfirmedAt());

        supplyLogCrudRepositoryMySQL.save(supplyLogEntity);
    }

}
