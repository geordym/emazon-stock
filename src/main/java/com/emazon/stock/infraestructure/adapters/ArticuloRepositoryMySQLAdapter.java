package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoryArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoryEntity;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.mapper.CategoryMapper;
import com.emazon.stock.infraestructure.repositories.ArticuloCrudRepositoryMySQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ArticuloRepositoryMySQLAdapter implements ArticuloRepositoryPort {


    private final ArticuloCrudRepositoryMySQL articuloCrudRepositoryMySQL;

    private final CategoryRepositoryPort categoryRepositoryPort;

    @PersistenceContext
    private EntityManager entityManager;



    public CategoryEntity actualizarCategoria(CategoryEntity categoria) {
        return entityManager.merge(categoria);
    }


    @Override
    @Transactional
    public Articulo saveArticulo(Articulo articulo) {

        ArticuloEntity articuloEntity = ArticuloMapper.domainToEntity(articulo);
        List<CategoryArticuloEntity> categoriaArticuloEntities = articuloEntity.getCategoriesArticulos();
        List<Long> categoriasIdList = categoriaArticuloEntities.stream().map(cae -> cae.getCategory().getIdCategory()).toList();

        List<CategoryEntity> categoriasList = categoryRepositoryPort.
                getCategoriesById(categoriasIdList).
                stream().map(CategoryMapper::domainToEntity).toList();

        categoriasList = categoriasList.stream().map(this::actualizarCategoria).toList();

        // Verificar si todas las categorías fueron encontradas
        if (categoriasList.size() != categoriasIdList.size()) {
            throw new IllegalArgumentException("Algunas categorías no fueron encontradas en la base de datos.");
        }

        List<CategoryArticuloEntity> categoriaArticuloEntityList = categoriasList.stream().map(categoriaEntity ->
                new CategoryArticuloEntity(articuloEntity,categoriaEntity)).toList();

        articuloEntity.setCategoriesArticulos(categoriaArticuloEntityList);

        ArticuloEntity articulo1 = articuloCrudRepositoryMySQL.save(articuloEntity);
        Articulo articuloDomain = ArticuloMapper.entityToDomain(articulo1);
        return articuloDomain;
    }

    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {

        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy()).ascending() : Sort.by(paginationParams.getSortBy()).descending()
        );

        Page<ArticuloEntity> articuloPage = articuloCrudRepositoryMySQL.findAll(pageRequest);
        List<Articulo> articuloList = articuloPage.getContent()
                .stream()
                .map(ArticuloMapper::entityToDomain)
                .toList();

        PaginationCustom<Articulo> pagination = new PaginationCustom<>(
                articuloList,
                articuloPage.getNumber(),
                articuloPage.getSize(),
                articuloPage.getTotalElements(),
                articuloPage.getTotalPages(),
                articuloPage.isLast()
        );
        return pagination;
    }


}
