package com.emazon.stock.infraestructure.client;

import com.emazon.stock.infraestructure.client.dto.UserInfoDto;
import com.emazon.stock.infraestructure.config.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-API", url = "${external.user.api.base-url}", configuration = FeignClientConfig.class)
public interface UserFeignClient {

    @GetMapping(value = "/api/users/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserInfoDto authenticateUserByToken(@RequestHeader("Authorization") String token);


}
