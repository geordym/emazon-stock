package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.services.ArticuloService;
import com.emazon.stock.application.services.CategoryService;
import com.emazon.stock.application.usecases.ArticuloImpl.CreateArticuloUseCaseImpl;
import com.emazon.stock.application.usecases.CategoriaImpl.CreateCategoryUseCaseImpl;
import com.emazon.stock.application.usecases.CategoriaImpl.ListCategoriesUseCaseImpl;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanArticuloConfiguration {
    @Bean
    ArticuloService articuloService(final ArticuloRepositoryPort articuloRepositoryPort){
        return new ArticuloService(new CreateArticuloUseCaseImpl(articuloRepositoryPort));
    }

}
