package com.emazon.stock.infraestructure.config;


import com.emazon.stock.domain.puertos.out.SupplyLogPersistencePort;
import com.emazon.stock.infraestructure.adapters.SupplyLogPersistenceMySQLAdapter;
import com.emazon.stock.infraestructure.repositories.SupplyLogCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanSupplyPersistenceConfiguration {

    @Bean
    public SupplyLogPersistencePort supplyLogPersistencePort(SupplyLogCrudRepositoryMySQL supplyLogCrudRepositoryMySQL){
        return new SupplyLogPersistenceMySQLAdapter(supplyLogCrudRepositoryMySQL);
    }

}
