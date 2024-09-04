package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.puertos.out.SupplyServicePort;
import com.emazon.stock.infraestructure.client.SupplyFeignClient;
import com.emazon.stock.infraestructure.client.dto.GenericResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyServiceFeignAdapter implements SupplyServicePort {

    private final SupplyFeignClient supplyFeignClient;
    @Override
    public void communicateSupplyReceived(Long supplyId) {
        GenericResponseDto genericResponseDto = supplyFeignClient.sendConfirmReceivedRequest(supplyId);
        System.out.println(genericResponseDto.getMessage());
    }

}
