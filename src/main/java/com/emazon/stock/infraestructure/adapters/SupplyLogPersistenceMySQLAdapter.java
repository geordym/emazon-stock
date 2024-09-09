package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.application.mapper.SupplyMapper;
import com.emazon.stock.domain.model.SupplyLog;
import com.emazon.stock.domain.puertos.out.SupplyLogPersistencePort;
import com.emazon.stock.infraestructure.entities.SupplyLogEntity;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.repositories.SupplyLogCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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

    @Override
    public void updateSupplyConfirmedAt(Long supplyId, LocalDateTime confirmedAt) {
        Optional<SupplyLogEntity> supplyLogEntityOpt = supplyLogCrudRepositoryMySQL.findSupplyLogBySupplyId(supplyId);
        if(supplyLogEntityOpt.isEmpty()){
            throw new IllegalArgumentException("The SupplyLog of SupplyId: " + supplyId + "Does not exist");
        }

        SupplyLogEntity supplyLogEntity = supplyLogEntityOpt.get();
        supplyLogEntity.setConfirmedAt(LocalDateTime.now());
        supplyLogCrudRepositoryMySQL.save(supplyLogEntity);
    }

}
