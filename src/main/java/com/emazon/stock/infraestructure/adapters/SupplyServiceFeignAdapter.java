package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.model.SupplyLog;
import com.emazon.stock.domain.puertos.out.SupplyServicePort;
import com.emazon.stock.infraestructure.client.SupplyFeignClient;
import com.emazon.stock.infraestructure.client.dto.GenericResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class SupplyServiceFeignAdapter implements SupplyServicePort {

    private final SupplyFeignClient supplyFeignClient;


    @Async("threadPoolExecutor")
    @Override
    public void communicateSupplyReceived(Long supplyId) throws InterruptedException {

        Thread.sleep(5000);
        try{
            GenericResponseDto genericResponseDto = supplyFeignClient.sendConfirmReceivedRequest(supplyId);
            System.out.println(genericResponseDto.getMessage());

        }catch (FeignException feignException) {
            System.out.println(feignException.getMessage());
        }


    }



}
