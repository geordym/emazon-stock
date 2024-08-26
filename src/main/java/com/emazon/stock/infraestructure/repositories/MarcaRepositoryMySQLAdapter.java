package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.infraestructure.mapper.MarcaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class MarcaRepositoryMySQLAdapter implements MarcaRepositoryPort {


    private final MarcaCrudRepositoryMySQL marcaCrudRepositoryMySQL;

    @Override
    public Marca guardarMarca(Marca marca) {
        return MarcaMapper.entityToDomain(marcaCrudRepositoryMySQL.save
                (MarcaMapper.domainToEntity(marca))
        );
    }

    @Override
    public Optional<Marca> obtenerMarcaPorNombre(String nombre) {
        return MarcaMapper.dtoToDomain(marcaCrudRepositoryMySQL.findByNombre(nombre));
    }

}
