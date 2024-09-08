package com.emazon.stock.infraestructure.mapper;


import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import com.emazon.stock.infraestructure.rest.dto.request.Articulo.CreateArticuloRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Articulo.ArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Articulo.CreateArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Categoria.CategoryResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Categoria.CategoryShortResponseDTO;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloMapper {


    private ArticuloMapper(){}


    public static Articulo requestCreateArticuloToDomain(CreateArticuloRequestDTO createArticuloRequestDTO){
        List<Category> categoriaArticuloList = Arrays.stream(createArticuloRequestDTO.getId_categories())
                .map(id -> new Category(id))
                .toList();

        Marca marca = new Marca(createArticuloRequestDTO.getId_marca());

        return new Articulo(createArticuloRequestDTO.getName(),
                createArticuloRequestDTO.getDescription(),
                createArticuloRequestDTO.getQuantity(),
                createArticuloRequestDTO.getPrice(),
                categoriaArticuloList,
                marca
        );
    }


    public static CreateArticuloResponseDTO domainToRequestCreateResponse(Articulo articulo){

        List<Category> categorias = articulo.getCategories();
        List<CategoryResponseDTO> categoriaResponseDTOS;
        categoriaResponseDTOS = categorias.stream().map(CategoryMapper::domainToDto).toList();
        return new CreateArticuloResponseDTO(articulo.getIdArticulo(),articulo.getNombre(),
                articulo.getDescripcion(), articulo.getCantidad(), articulo.getPrecio(),
                categoriaResponseDTOS,
                articulo.getMarca()
        );
    }

    public static List<CategoryResponseDTO> listCategoriaArticuloDomainToArticuloResponseDTOList(List<Category> categoriaArticuloDomainList) {
        return categoriaArticuloDomainList.stream().map(categoriaArticuloDomain -> {
            return new CategoryResponseDTO(categoriaArticuloDomain.getId_categoria(), categoriaArticuloDomain.getName(), categoriaArticuloDomain.getDescription());
        }).collect(Collectors.toList());
    }

    public static List<CategoryShortResponseDTO> listCategoriaArticuloDomainToArticuloShortResponseDTOList(List<Category> categoriaArticuloDomainList) {
        return categoriaArticuloDomainList.stream().map(categoriaArticuloDomain -> {
            return new CategoryShortResponseDTO(categoriaArticuloDomain.getId_categoria(), categoriaArticuloDomain.getName());
        }).collect(Collectors.toList());
    }

    public static ArticuloResponseDTO domainToDto(Articulo articulo) {
        return new ArticuloResponseDTO(articulo.getIdArticulo(),
                articulo.getNombre(),
                articulo.getDescripcion(), articulo.getCantidad(),
                articulo.getPrecio(),
                ArticuloMapper.listCategoriaArticuloDomainToArticuloShortResponseDTOList(articulo.getCategories()),
                MarcaMapper.marcaDomainToShortDto(articulo.getMarca())
        );
    }




    public static ArticuloEntity domainToEntity(Articulo articulo) {
        ArticuloEntity articuloEntity = new ArticuloEntity();

        if (articulo.getIdArticulo() != null) {
            articuloEntity.setIdArticulo(articulo.getIdArticulo());
        }

        if (articulo.getNombre() != null) {
            articuloEntity.setNombre(articulo.getNombre());
        }

        if (articulo.getDescripcion() != null) {
            articuloEntity.setDescripcion(articulo.getDescripcion());
        }

        if (articulo.getCantidad() != null) {
            articuloEntity.setCantidad(articulo.getCantidad());
        }

        if (articulo.getPrecio() != null) {
            articuloEntity.setPrecio(articulo.getPrecio());
        }

        if (articulo.getCategories() != null) {
            articuloEntity.setCategories(CategoryMapper.domainListCategoriesToEntityList(articulo.getCategories()));
        }

        if (articulo.getMarca() != null) {
            articuloEntity.setMarca(MarcaMapper.domainToEntity(articulo.getMarca()));
        }

        return articuloEntity;
    }


    public static Articulo entityToDomain(ArticuloEntity articulo){
        return new Articulo(articulo.getIdArticulo(),
                articulo.getNombre(),
                articulo.getDescripcion(),
                articulo.getCantidad(),
                articulo.getPrecio(),
                CategoryMapper.entityListCategoriesToDomainList(articulo.getCategories()),
                MarcaMapper.entityToDomain(articulo.getMarca())
                );
    }



}
