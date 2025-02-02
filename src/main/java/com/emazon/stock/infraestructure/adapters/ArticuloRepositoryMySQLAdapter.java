package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.exception.UpdatingStockException;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoryEntity;
import com.emazon.stock.infraestructure.enums.ArticleSortBy;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.mapper.CategoryMapper;
import com.emazon.stock.infraestructure.repositories.ArticuloCrudRepositoryMySQL;
import io.swagger.models.auth.In;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ArticuloRepositoryMySQLAdapter implements ArticuloRepositoryPort {


    private final ArticuloCrudRepositoryMySQL articuloCrudRepositoryMySQL;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Articulo saveArticulo(Articulo articulo) {
        ArticuloEntity articuloSaved = articuloCrudRepositoryMySQL.save(ArticuloMapper.domainToEntity(articulo));
        Long idArticleSaved = articuloSaved.getIdArticulo();
        clearCacheEntity();
        ArticuloEntity articleFetchedWithRelations = articuloCrudRepositoryMySQL.findByIdWithCategoriesAndMark(idArticleSaved);
        Articulo articuloDomain = ArticuloMapper.entityToDomain(articleFetchedWithRelations);
        return articuloDomain;
    }



    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {

        ArticleSortBy sortBy = ArticleSortBy.fromValue(paginationParams.getSortBy());

        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(sortBy.toEntity()).ascending() : Sort.by(sortBy.toEntity()).descending()
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

    @Override
    public Optional<Articulo> findArticleById(Long articleId) {
        Optional<ArticuloEntity> articuloEntityOptional = articuloCrudRepositoryMySQL.findById(articleId);
        if(articuloEntityOptional.isEmpty()){
            return Optional.empty();

        }

        return Optional.of(ArticuloMapper.entityToDomain(articuloEntityOptional.get()));
    }

    @Transactional
    @Override
    public void updateArticleStock(Long articleId, Integer quantity) throws UpdatingStockException {
        try {
            articuloCrudRepositoryMySQL.updateArticleStock(articleId, quantity);
        } catch (DataAccessException e) {
            throw new UpdatingStockException(e);
        }
    }


    private void clearCacheEntity(){
        entityManager.flush();
        entityManager.clear();
    }

}
