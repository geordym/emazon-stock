package com.emazon.stock.infraestructure.mapper;


import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.CategoryArticulo;
import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoryArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoryEntity;
import com.emazon.stock.infraestructure.rest.dto.request.Articulo.CreateArticuloRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Articulo.CreateArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Categoria.CategoryResponseDTO;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloMapper {


    private ArticuloMapper(){}


    public static Articulo requestCreateArticuloToDomain(CreateArticuloRequestDTO createArticuloRequestDTO){
        List<CategoryArticulo> categoriaArticuloList = Arrays.stream(createArticuloRequestDTO.getId_categories())
                .map(id -> new CategoryArticulo(new Category(id)))
                .toList();


        return new Articulo(createArticuloRequestDTO.getName(),
                createArticuloRequestDTO.getDescription(),
                createArticuloRequestDTO.getQuantity(),
                createArticuloRequestDTO.getPrice(),
                categoriaArticuloList
        );
    }


    public static CreateArticuloResponseDTO domainToRequestCreateResponse(Articulo articulo){

        List<CategoryArticulo> categoriasArticle = articulo.getCategories();
        List<Category> categorias = categoriasArticle.stream().map(CategoryArticulo::getCategory).toList();
        List<CategoryResponseDTO> categoriaResponseDTOS;
        categoriaResponseDTOS = categorias.stream().map(CategoryMapper::domainToDto).toList();
        return new CreateArticuloResponseDTO(articulo.getIdArticulo(),articulo.getNombre(),
                articulo.getDescripcion(), articulo.getCantidad(), articulo.getPrecio(),
                categoriaResponseDTOS
        );
    }

    public static List<CategoryArticuloEntity> categoriaArticuloDomainToEntity(List<CategoryArticulo> categoriaArticuloList) {
        return categoriaArticuloList.stream().map(categoriaArticulo -> {
            CategoryArticuloEntity entity = new CategoryArticuloEntity();
            entity.setCategory(new CategoryEntity(categoriaArticulo.getCategory().getId_categoria(), categoriaArticulo.getCategory().getName(), categoriaArticulo.getCategory().getDescription())); // Asumiendo que `getIdCategoria()` existe en `Categoria`
            // Si tienes el `ArticuloEntity` mapeado, puedes setearlo también
            return entity;
        }).collect(Collectors.toList());
    }

    public static List<CategoryArticulo> categoriaArticuloEntityToDomain(List<CategoryArticuloEntity> categoryArticuloList) {
        return categoryArticuloList.stream().map(categoriaArticulo -> {
            return new CategoryArticulo(new Category(categoriaArticulo.getCategory().getIdCategory(), categoriaArticulo.getCategory().getName(), categoriaArticulo.getCategory().getDescription()));
        }).collect(Collectors.toList());
    }


    public static ArticuloEntity domainToEntity(Articulo articulo){

        return new ArticuloEntity(articulo.getIdArticulo(),
                articulo.getNombre(), articulo.getDescripcion(),
                articulo.getCantidad(), articulo.getPrecio(), ArticuloMapper.categoriaArticuloDomainToEntity(articulo.getCategories()));
    }

    public static Articulo entityToDomain(ArticuloEntity articulo){

        return new Articulo(articulo.getIdArticulo(),
                articulo.getNombre(), articulo.getDescripcion(),
                articulo.getCantidad(), articulo.getPrecio(), ArticuloMapper.categoriaArticuloEntityToDomain(articulo.getCategories()));
    }



}
