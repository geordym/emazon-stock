package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.model.SupplyLog;
import com.emazon.stock.domain.puertos.out.SupplyLogPersistencePort;
import com.emazon.stock.domain.puertos.out.SupplyServicePort;
import com.emazon.stock.infraestructure.client.SupplyFeignClient;
import com.emazon.stock.infraestructure.client.dto.GenericResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SupplyServiceFeignAdapter implements SupplyServicePort {

    private final SupplyFeignClient supplyFeignClient;
    private final SupplyLogPersistencePort supplyLogPersistencePort;

    @Async("threadPoolExecutor")
    @Override
    public void communicateSupplyReceived(Long supplyId) throws InterruptedException {

        Thread.sleep(2000);
        try{
            GenericResponseDto genericResponseDto = supplyFeignClient.sendConfirmReceivedRequest(supplyId);
            supplyLogPersistencePort.updateSupplyConfirmedAt(supplyId, LocalDateTime.now());
            System.out.println(genericResponseDto.getMessage());

        }catch (FeignException feignException) {
            System.out.println(feignException.getMessage());
        }


    }

    @Async("threadPoolExecutor")
    @Override
    public void communicateSupplyRejected(Long supplyId) throws InterruptedException {
        Thread.sleep(2000);
        try{
            GenericResponseDto genericResponseDto = supplyFeignClient.sendRejectedMessageRequest(supplyId);
           // supplyLogPersistencePort.updateSupplyConfirmedAt(supplyId, LocalDateTime.now());
            System.out.println(genericResponseDto.getMessage());

        }catch (FeignException feignException) {
            System.out.println("Exception: + " + feignException.getMessage());
        }
    }


}
