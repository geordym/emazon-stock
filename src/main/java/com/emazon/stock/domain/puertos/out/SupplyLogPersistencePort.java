package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.SupplyLog;

import java.util.Optional;

public interface SupplyLogPersistencePort {

    Optional<SupplyLog> findSupplyLogBySupplyId(Long supplyId);

    void createSupplyLog(SupplyLog supplyLog);

}
