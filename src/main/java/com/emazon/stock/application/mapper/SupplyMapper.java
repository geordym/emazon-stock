package com.emazon.stock.application.mapper;

import com.emazon.stock.domain.model.SupplyLog;
import com.emazon.stock.infraestructure.entities.SupplyLogEntity;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;

public class SupplyMapper {

    public static SupplyLog entityToDomain(SupplyLogEntity supplyLogEntity){
        SupplyLog supplyLog = new SupplyLog();
        supplyLog.setId(supplyLogEntity.getId());
        supplyLog.setSupplyId(supplyLogEntity.getSupplyId());
        supplyLog.setArticle(ArticuloMapper.entityToDomain(supplyLogEntity.getArticle()));
        supplyLog.setReceivedAt(supplyLogEntity.getReceivedAt());
        supplyLog.setConfirmedAt(supplyLogEntity.getConfirmedAt());
        supplyLog.setStatus(supplyLogEntity.getStatus());
        return supplyLog;
    }

}
