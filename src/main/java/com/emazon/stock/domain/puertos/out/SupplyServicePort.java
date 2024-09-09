package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.SupplyLog;

public interface SupplyServicePort {

    void communicateSupplyReceived(Long supplyId) throws InterruptedException;
    void communicateSupplyRejected(Long supplyId) throws InterruptedException;



}
