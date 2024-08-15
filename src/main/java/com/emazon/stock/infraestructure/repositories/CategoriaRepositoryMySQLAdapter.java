package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoriaRepositoryMySQLAdapter implements CategoriaRepositoryPort {


    private final CategoriaCrudRepositoryMySQL categoriaCrudRepositoryMySQL;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return CategoriaMapper.entityToDto(categoriaCrudRepositoryMySQL.save(CategoriaMapper.domainToEntity(categoria)));
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorNombre(String nombre) {
        return CategoriaMapper.optionalCategoriaEntityToModelCategoria(
                categoriaCrudRepositoryMySQL.findByNombre(nombre));
    }


}
