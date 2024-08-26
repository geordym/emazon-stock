package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.services.CategoryService;
import com.emazon.stock.application.services.MarcaService;


import com.emazon.stock.application.usecases.CategoriaImpl.CreateCategoryUseCaseImpl;
import com.emazon.stock.application.usecases.CategoriaImpl.ListCategoriesUseCaseImpl;
import com.emazon.stock.application.usecases.MarcaImpl.CreateMarcaUseCaseImpl;
import com.emazon.stock.application.usecases.MarcaImpl.ListMarcasUseCaseImpl;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMarcaConfiguration {

    @Bean
    CategoryService categoryService(final CategoryRepositoryPort categoryRepositoryPort){
        return new CategoryService(new CreateCategoryUseCaseImpl(categoryRepositoryPort), new ListCategoriesUseCaseImpl(categoryRepositoryPort));
    }

    @Bean
    MarcaService marcaService(final MarcaRepositoryPort marcaRepositoryPort){
        return new MarcaService(new ListMarcasUseCaseImpl(marcaRepositoryPort), new CreateMarcaUseCaseImpl(marcaRepositoryPort));
    }


}
