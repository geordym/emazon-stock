package com.emazon.stock.infraestructure.config;


import com.emazon.stock.application.services.CategoriaService;


import com.emazon.stock.application.usecases.CrearCategoriaUseCaseImpl;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;


import com.emazon.stock.infraestructure.repositories.CategoriaCrudRepositoryMySQL;
import com.emazon.stock.infraestructure.repositories.CategoriaRepositoryMySQLAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCategoriaConfiguration {


    @Bean
    CategoriaRepositoryPort categoriaRepositoryPort(final CategoriaCrudRepositoryMySQL categoriaCrudRepositoryMySQL){
        return new CategoriaRepositoryMySQLAdapter(categoriaCrudRepositoryMySQL);
    }

    @Bean
    CategoriaService categoriaService(final CategoriaRepositoryPort categoriaRepositoryPort){
        return new CategoriaService(new CrearCategoriaUseCaseImpl(categoriaRepositoryPort));
    }




}
