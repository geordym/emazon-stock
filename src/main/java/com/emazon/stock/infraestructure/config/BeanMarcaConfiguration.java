package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.implementations.CategoryService;
import com.emazon.stock.application.implementations.MarcaService;


import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.usecases.CategoryImpl.CategoryUseCasesImpl;
import com.emazon.stock.domain.usecases.MarcaImpl.MarcaUseCasesImpl;
import com.emazon.stock.domain.usecases.MarcaImpl.validators.MarcaValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMarcaConfiguration {



    @Bean
    MarcaService marcaService(final MarcaRepositoryPort marcaRepositoryPort,  MarcaValidator marcaValidator){
        return new MarcaService(new MarcaUseCasesImpl(marcaRepositoryPort, marcaValidator));
    }


}
