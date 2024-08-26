package com.emazon.stock.infraestructure.mapper;


import com.emazon.stock.domain.model.Category;
import com.emazon.stock.infraestructure.entities.CategoryEntity;
import com.emazon.stock.infraestructure.rest.dto.request.Categoria.CategoryCreateRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Categoria.CategoryResponseDTO;

import java.util.Optional;

public class CategoryMapper {


    private CategoryMapper() {
    }

    public static CategoryEntity dtoToEntity(CategoryCreateRequestDTO categoria){

        return new CategoryEntity(0L,categoria.getName(),categoria.getDescription());
    }


    public static CategoryEntity domainToEntity(Category categoria){
        return new CategoryEntity(categoria.getId_categoria(), categoria.getName(), categoria.getDescription());
    }

    public static Optional<Category> optionalCategoriaEntityToModelCategoria(Optional<CategoryEntity> categoriaEntityOptional) {
        return categoriaEntityOptional.map(categoriaEntity ->
                new Category(
                        categoriaEntity.getIdCategory(),
                        categoriaEntity.getName(),
                        categoriaEntity.getDescription()
                )
        );
    }

    public static Category entityToDto(CategoryEntity category){
        return new Category(category.getIdCategory(), category.getName(), category.getDescription());
    }

    public static Category dtoToDomain(CategoryCreateRequestDTO category){
        return new Category(0L, category.getName(), category.getDescription());
    }

    public static CategoryResponseDTO domainToDto(Category categoria){
        return new CategoryResponseDTO(categoria.getId_categoria(), categoria.getName(), categoria.getDescription());
    }

    public static Category entityToDomain(CategoryEntity categoria){
        return new Category(categoria.getIdCategory(), categoria.getName(), categoria.getDescription());
    }



    public static Category dtoToDomain(CategoryResponseDTO categoria){
        return new Category(categoria.getId_category(), categoria.getName(), categoria.getDescription());
    }



}