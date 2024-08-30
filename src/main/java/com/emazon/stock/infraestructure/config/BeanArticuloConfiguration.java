package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.implementations.ArticuloService;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.usecases.ArticuloImpl.ArticuloUseCasesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanArticuloConfiguration {
    @Bean
    ArticuloService articuloService(final ArticuloRepositoryPort articuloRepositoryPort){
        return new ArticuloService(new ArticuloUseCasesImpl(articuloRepositoryPort));
    }



}
