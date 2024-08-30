package com.emazon.stock.infraestructure.mapper;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.infraestructure.entities.MarcaEntity;
import com.emazon.stock.infraestructure.rest.dto.request.Marca.MarcaCreateRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Marca.MarcaCreateResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Marca.MarcaShortResponseDTO;

import java.util.Optional;

public class MarcaMapper {

    private MarcaMapper(){}



    public static MarcaEntity dtoToEntity(MarcaCreateRequestDTO marca){

        return new MarcaEntity(marca.getNombre(),marca.getDescripcion());
    }


    public static MarcaEntity domainToEntity(Marca marca){
        return new MarcaEntity(marca.getId(),marca.getNombre(),marca.getDescripcion());
    }


    public static Marca entityToDomain(MarcaEntity marca){
        return new Marca(marca.getIdMarca(), marca.getNombre(), marca.getDescripcion());
    }

    public static MarcaShortResponseDTO marcaDomainToShortDto(Marca marca){
        return new MarcaShortResponseDTO(marca.getId(),marca.getNombre());
    }



    public static Marca dtoToDomain(MarcaCreateRequestDTO marca){
        return new Marca(marca.getNombre(),marca.getDescripcion());
    }

    public static MarcaCreateResponseDTO domainToDto(Marca marca){
        return new MarcaCreateResponseDTO(marca.getId(),marca.getNombre(),marca.getDescripcion());
    }


    public static Optional<Marca> dtoToDomain(Optional<MarcaEntity> marcaEntityOpt) {
        return marcaEntityOpt.map(marcaEntity ->
                new Marca(marcaEntity.getIdMarca(), marcaEntity.getNombre(), marcaEntity.getDescripcion())
        );
    }


}
