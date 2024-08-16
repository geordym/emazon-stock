package com.emazon.stock.infraestructure.mapper;

import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.infraestructure.entities.CategoriaEntity;
import com.emazon.stock.infraestructure.rest.dto.request.CategoriaRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CategoriaResponseDTO;

import java.util.Optional;

public class CategoriaMapper {


    private CategoriaMapper() {
    }

    public static CategoriaEntity dtoToEntity(CategoriaRequestDTO categoria){

        return new CategoriaEntity(categoria.getId_categoria(),categoria.getNombre(),categoria.getDescripcion());
    }


    public static CategoriaEntity domainToEntity(Categoria categoria){
        return new CategoriaEntity(categoria.getIdCategoria(), categoria.getNombre(), categoria.getDescripcion());
    }

    public static Optional<Categoria> optionalCategoriaEntityToModelCategoria(Optional<CategoriaEntity> categoriaEntityOptional) {
        return categoriaEntityOptional.map(categoriaEntity ->
                new Categoria(
                        categoriaEntity.getId_categoria(),
                        categoriaEntity.getNombre(),
                        categoriaEntity.getDescripcion()
                )
        );
    }

    public static Categoria entityToDomain(CategoriaEntity categoria){
        return new Categoria(categoria.getId_categoria(), categoria.getNombre(), categoria.getDescripcion());
    }

    public static Categoria dtoToDomain(CategoriaRequestDTO categoria){
        return new Categoria(categoria.getId_categoria(), categoria.getNombre(), categoria.getDescripcion());
    }

    public static CategoriaResponseDTO domainToDto(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getIdCategoria(), categoria.getNombre(), categoria.getDescripcion());
    }


}
