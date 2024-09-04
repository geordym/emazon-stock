package com.emazon.stock.infraestructure.config;

import com.emazon.stock.domain.puertos.out.SupplyServicePort;
import com.emazon.stock.infraestructure.adapters.SupplyServiceFeignAdapter;
import com.emazon.stock.infraestructure.client.SupplyFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanSupplyConfiguration {

    @Bean
    public SupplyServicePort supplyServicePort(SupplyFeignClient supplyFeignClient){
        return new SupplyServiceFeignAdapter(supplyFeignClient);
    }
}
