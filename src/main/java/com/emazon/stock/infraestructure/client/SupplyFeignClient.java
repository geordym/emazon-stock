package com.emazon.stock.infraestructure.client;

import com.emazon.stock.infraestructure.client.dto.GenericResponseDto;
import com.emazon.stock.infraestructure.client.dto.UserInfoDto;
import com.emazon.stock.infraestructure.config.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TRANSACTION-API", url = "${external.transaction.api.base-url}", configuration = FeignClientConfig.class)
public interface SupplyFeignClient {

    @PutMapping(value = "/api/supply/confirm/{supplyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    GenericResponseDto sendConfirmReceivedRequest(@PathVariable Long supplyId);

    @PutMapping(value = "/api/supply/cancel/{supplyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    GenericResponseDto sendRejectedMessageRequest(@PathVariable Long supplyId);

}