package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import com.emazon.stock.infraestructure.repositories.CategoriaCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;



import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoriaRepositoryMySQLAdapter implements CategoriaRepositoryPort {


    private final CategoriaCrudRepositoryMySQL categoriaCrudRepositoryMySQL;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return CategoriaMapper.entityToDomain(categoriaCrudRepositoryMySQL.save(CategoriaMapper.domainToEntity(categoria)));
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorNombre(String nombre) {
        return CategoriaMapper.optionalCategoriaEntityToModelCategoria(
                categoriaCrudRepositoryMySQL.findByNombre(nombre));
    }

    @Override
    public List<Categoria> obtenerCategoriasPorArticulo(Articulo articulo) {
        return new ArrayList<>();
    }

    @Override
    public List<Categoria> obtenerCategoriasPorId(List<Long> idList) {
        return categoriaCrudRepositoryMySQL.findAllById(idList).stream().map(CategoriaMapper::entityToDomain).toList();
    }


}
