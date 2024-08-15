package com.emazon.stock.infraestructure.mapper;

import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.infraestructure.entities.CategoriaEntity;

import java.util.Optional;

public class CategoriaMapper {


    private CategoriaMapper() {
    }

    public static CategoriaEntity dtoToEntity(Categoria categoria){

        return new CategoriaEntity(categoria.getId_categoria(),categoria.getNombre(),categoria.getDescripcion());
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

    public static Categoria entityToDto(CategoriaEntity categoria){
        return new Categoria(categoria.getId_categoria(), categoria.getNombre(), categoria.getDescripcion());
    }

}
