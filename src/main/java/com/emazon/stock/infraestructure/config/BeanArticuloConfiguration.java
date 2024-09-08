package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.implementations.ArticuloService;
import com.emazon.stock.domain.puertos.out.*;
import com.emazon.stock.domain.usecases.ArticuloImpl.ArticuloUseCasesImpl;
import com.emazon.stock.domain.usecases.ArticuloImpl.validators.ArticuloValidator;
import com.emazon.stock.domain.usecases.CategoryImpl.validators.CategoryValidator;
import com.emazon.stock.domain.usecases.MarcaImpl.validators.MarcaValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanArticuloConfiguration {

    @Bean
    ArticuloService articuloService(final ArticuloRepositoryPort articuloRepositoryPort,
                                    ArticuloValidator articuloValidator,
                                    SupplyServicePort supplyServicePort,
                                    SupplyLogPersistencePort supplyLogPersistencePort
                                    ){
        return new ArticuloService(
                new ArticuloUseCasesImpl(
                articuloRepositoryPort,
                articuloValidator, supplyServicePort,
                supplyLogPersistencePort)
        );
    }

    @Bean
    public ArticuloValidator articuloValidator(){
        return new ArticuloValidator();
    }

    @Bean
    public CategoryValidator categoryValidator(CategoryRepositoryPort categoryRepositoryPort){
        return new CategoryValidator(categoryRepositoryPort);
    }

    @Bean
    public MarcaValidator marcaValidator(MarcaRepositoryPort marcaRepositoryPort){
        return new MarcaValidator(marcaRepositoryPort);
    }


}
