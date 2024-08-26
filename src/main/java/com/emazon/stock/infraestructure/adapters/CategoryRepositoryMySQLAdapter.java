package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.entities.CategoryEntity;
import com.emazon.stock.infraestructure.mapper.CategoryMapper;
import com.emazon.stock.infraestructure.repositories.CategoryCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryRepositoryMySQLAdapter implements CategoryRepositoryPort {

    private final CategoryCrudRepositoryMySQL categoryCrudRepositoryMySQL;


    @Override
    public Category saveCategory(Category categoria) {
        return CategoryMapper.entityToDto(categoryCrudRepositoryMySQL.save(CategoryMapper.domainToEntity(categoria)));
    }

    @Override
    public Optional<Category> getCategoryByName(String nombre) {
        return CategoryMapper.optionalCategoriaEntityToModelCategoria(
                categoryCrudRepositoryMySQL.findByName(nombre));
    }

    @Override
    public PaginationCustom listCategories(PaginationParams paginationParams) {

        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy()).ascending() : Sort.by(paginationParams.getSortBy()).descending()
        );

        Page<CategoryEntity> categoryPage = categoryCrudRepositoryMySQL.findAll(pageRequest);
        List<Category> categoryList = categoryPage.getContent()
                .stream()
                .map(CategoryMapper::entityToDomain)
                .toList();

        PaginationCustom<Category> pagination = new PaginationCustom<>(
                categoryList,
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages(),
                categoryPage.isLast()
        );
        return pagination;
    }

    @Override
    public List<Category> getCategoriesByArticulo(Articulo articulo) {
        return new ArrayList<>();
    }

    @Override
    public List<Category> getCategoriesById(List<Long> idList) {
        return categoryCrudRepositoryMySQL.findAllById(idList).stream().map(CategoryMapper::entityToDomain).toList();
    }


}
