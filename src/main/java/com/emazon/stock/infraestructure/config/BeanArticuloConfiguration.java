package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.services.ArticuloService;
import com.emazon.stock.application.usecases.CrearArticuloUseCaseImpl;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import com.emazon.stock.infraestructure.adapters.ArticuloRepositoryMySQLAdapter;
import com.emazon.stock.infraestructure.repositories.ArticuloCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanArticuloConfiguration {

    @Bean
    public ArticuloRepositoryPort articuloRepositoryPort(
            final ArticuloCrudRepositoryMySQL articuloCrudRepositoryMySQL, final CategoriaRepositoryPort categoriaRepositoryPort){
        return new ArticuloRepositoryMySQLAdapter(articuloCrudRepositoryMySQL, categoriaRepositoryPort);
    }

    @Bean
    public ArticuloService articuloService(final ArticuloRepositoryPort articuloRepositoryPort){
        return new ArticuloService(new CrearArticuloUseCaseImpl(articuloRepositoryPort));
    }

}
