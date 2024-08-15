package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.services.MarcaService;


import com.emazon.stock.application.usecases.ListarMarcasUseCaseImpl;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMarcaConfiguration {

    @Bean
    MarcaService marcaService(final MarcaRepositoryPort marcaRepositoryPort){
        return new MarcaService(new ListarMarcasUseCaseImpl(marcaRepositoryPort));
    }


}
