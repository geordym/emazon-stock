package com.emazon.stock.infraestructure.mapper;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.model.CategoriaArticulo;
import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoriaArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoriaEntity;
import com.emazon.stock.infraestructure.rest.dto.request.CreateArticuloRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.ArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CategoriaResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CreateArticuloResponseDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloMapper {


    private ArticuloMapper(){}


    public static Articulo requestCreateArticuloToDomain(CreateArticuloRequestDTO createArticuloRequestDTO){
        List<CategoriaArticulo> categoriaArticuloList = Arrays.stream(createArticuloRequestDTO.getId_categorias())
                .map(id -> new CategoriaArticulo(new Categoria(id.longValue())))
                .toList();


        return new Articulo(createArticuloRequestDTO.getNombre(),
                createArticuloRequestDTO.getDescripcion(),
                createArticuloRequestDTO.getCantidad(),
                createArticuloRequestDTO.getPrecio(),
                categoriaArticuloList
                );
    }


    public static CreateArticuloResponseDTO domainToRequestCreateResponse(Articulo articulo){

        List<CategoriaArticulo> categoriasArticle = articulo.getCategorias();
        List<Categoria> categorias = categoriasArticle.stream().map(CategoriaArticulo::getCategoria).toList();
        List<CategoriaResponseDTO> categoriaResponseDTOS;
        categoriaResponseDTOS = categorias.stream().map(CategoriaMapper::domainToDto).toList();
        return new CreateArticuloResponseDTO(articulo.getIdArticulo(),articulo.getNombre(),
                articulo.getDescripcion(), articulo.getCantidad(), articulo.getPrecio(),
                categoriaResponseDTOS
                );
    }

    public static List<CategoriaArticuloEntity> categoriaArticuloDomainToEntity(List<CategoriaArticulo> categoriaArticuloList) {
        return categoriaArticuloList.stream().map(categoriaArticulo -> {
            CategoriaArticuloEntity entity = new CategoriaArticuloEntity();
            entity.setCategoria(new CategoriaEntity(categoriaArticulo.getCategoria().getIdCategoria())); // Asumiendo que `getIdCategoria()` existe en `Categoria`
            // Si tienes el `ArticuloEntity` mapeado, puedes setearlo también
            return entity;
        }).collect(Collectors.toList());
    }

    public static List<CategoriaArticulo> categoriaArticuloEntityToDomain(List<CategoriaArticuloEntity> categoriaArticuloListEntity) {
        return categoriaArticuloListEntity.stream().map(categoriaArticuloEntity -> {
            return new CategoriaArticulo(new Categoria(categoriaArticuloEntity.getCategoria().getId_categoria(), categoriaArticuloEntity.getCategoria().getNombre(), categoriaArticuloEntity.getCategoria().getDescripcion()));
        }).collect(Collectors.toList());
    }


    public static ArticuloEntity domainToEntity(Articulo articulo){

        return new ArticuloEntity(articulo.getIdArticulo(),
                articulo.getNombre(), articulo.getDescripcion(),
                articulo.getCantidad(), articulo.getPrecio(), ArticuloMapper.categoriaArticuloDomainToEntity(articulo.getCategorias()));
    }

    public static Articulo entityToDomain(ArticuloEntity articulo){

        return new Articulo(articulo.getIdArticulo(),
                articulo.getNombre(), articulo.getDescripcion(),
                articulo.getCantidad(), articulo.getPrecio(),
                ArticuloMapper.categoriaArticuloEntityToDomain(articulo.getCategorias()

                ));
    }

    public static List<CategoriaResponseDTO> listCategoriaArticuloDomainToArticuloResponseDTOList(List<CategoriaArticulo> categoriaArticuloDomainList) {
        return categoriaArticuloDomainList.stream().map(categoriaArticuloDomain -> {
            return new CategoriaResponseDTO(categoriaArticuloDomain.getCategoria().getNombre(), categoriaArticuloDomain.getCategoria().getDescripcion());
        }).collect(Collectors.toList());
    }

    public static ArticuloResponseDTO domainToDto(Articulo articulo) {
        return new ArticuloResponseDTO(articulo.getIdArticulo(),
                articulo.getNombre(),
                articulo.getDescripcion(), articulo.getCantidad(),
                articulo.getPrecio(),
                ArticuloMapper.listCategoriaArticuloDomainToArticuloResponseDTOList(articulo.getCategorias())
                );
    }

}
