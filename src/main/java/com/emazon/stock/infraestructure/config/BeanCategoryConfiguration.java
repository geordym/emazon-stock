package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.implementations.CategoryService;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.usecases.CategoryImpl.CategoryUseCasesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCategoryConfiguration {


    @Bean
    CategoryService categoryService(final CategoryRepositoryPort categoryRepositoryPort){
        return new CategoryService(new CategoryUseCasesImpl(categoryRepositoryPort));
    }

}
