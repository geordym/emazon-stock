package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.entities.MarcaEntity;
import com.emazon.stock.infraestructure.mapper.MarcaMapper;
import com.emazon.stock.infraestructure.repositories.MarcaCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class MarcaRepositoryMySQLAdapter implements MarcaRepositoryPort {


    private final MarcaCrudRepositoryMySQL marcaCrudRepositoryMySQL;


    @Override
    public PaginationCustom listMarcas(PaginationParams paginationParams) {
        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy()).ascending() : Sort.by(paginationParams.getSortBy()).descending()
        );
        Page<MarcaEntity> marcaPage = marcaCrudRepositoryMySQL.findAll(pageRequest);
        List<Marca> marcaList = marcaPage.getContent()
                .stream()
                .map(MarcaMapper::entityToDomain)
                .toList();
        PaginationCustom<Marca> pagination = new PaginationCustom<>(
                marcaList,
                marcaPage.getNumber(),
                marcaPage.getSize(),
                marcaPage.getTotalElements(),
                marcaPage.getTotalPages(),
                marcaPage.isLast()
        );
        return pagination;
    }

    @Override
    public Marca saveMarca(Marca marca) {
        return MarcaMapper.entityToDomain(marcaCrudRepositoryMySQL.save
                (MarcaMapper.domainToEntity(marca))
        );
    }


    @Override
    public Optional<Marca> obtenerMarcaPorNombre(String nombre) {
        return MarcaMapper.dtoToDomain(marcaCrudRepositoryMySQL.findByNombre(nombre));
    }

}
