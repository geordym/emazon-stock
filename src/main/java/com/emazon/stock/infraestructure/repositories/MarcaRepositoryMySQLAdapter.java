package com.emazon.stock.infraestructure.repositories;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.entities.MarcaEntity;
import com.emazon.stock.infraestructure.mapper.MarcaMapper;
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
    public List<Marca> listMarcas(PaginationParams paginationParams) {
        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy()).ascending() : Sort.by(paginationParams.getSortBy()).descending()
        );
        Page<MarcaEntity> categoriaPage = marcaCrudRepositoryMySQL.findAll(pageRequest);
        return categoriaPage.getContent().stream().map(MarcaMapper::entityToDomain).toList();
    }




}
