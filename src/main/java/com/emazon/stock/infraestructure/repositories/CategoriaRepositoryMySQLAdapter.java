package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.entities.CategoriaEntity;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;



import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CategoriaRepositoryMySQLAdapter implements CategoriaRepositoryPort {


    private final CategoriaCrudRepositoryMySQL categoriaCrudRepositoryMySQL;


    @Override
    public Optional<Categoria> obtenerCategoriaPorNombre(String nombre) {
        return CategoriaMapper.optionalCategoriaEntityToModelCategoria(
                categoriaCrudRepositoryMySQL.findByNombre(nombre));
    }

    @Override
    public List<Categoria> listarCategorias(PaginationParams paginationParams) {


        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy()).ascending() : Sort.by(paginationParams.getSortBy()).descending()
        );

        Page<CategoriaEntity> categoriaPage = categoriaCrudRepositoryMySQL.findAll(pageRequest);

        return categoriaPage.getContent().stream().map(CategoriaMapper::entityToDomain).toList();

    }


}
