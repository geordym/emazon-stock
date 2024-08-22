package com.emazon.stock.infraestructure.adapters;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoriaArticuloEntity;
import com.emazon.stock.infraestructure.entities.CategoriaEntity;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import com.emazon.stock.infraestructure.repositories.ArticuloCrudRepositoryMySQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ArticuloRepositoryMySQLAdapter implements ArticuloRepositoryPort {


    private final ArticuloCrudRepositoryMySQL articuloCrudRepositoryMySQL;

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Articulo> listArticles(PaginationParams paginationParams) {
        return articuloCrudRepositoryMySQL.findAll();
    }

    @Override
    @Transactional
    public Articulo guardarArticulo(Articulo articulo) {

        ArticuloEntity articuloEntity = ArticuloMapper.domainToEntity(articulo);
        List<CategoriaArticuloEntity> categoriaArticuloEntities = articuloEntity.getCategoriasArticulos();
        List<Long> categoriasIdList = categoriaArticuloEntities.stream().map(cae -> cae.getCategoria().getId_categoria()).toList();

        List<CategoriaEntity> categoriasList = categoriaRepositoryPort.
                obtenerCategoriasPorId(categoriasIdList).
                stream().map(CategoriaMapper::domainToEntity).toList();

        categoriasList = categoriasList.stream().map(this::actualizarCategoria).toList();

        // Verificar si todas las categorías fueron encontradas
        if (categoriasList.size() != categoriasIdList.size()) {
            throw new IllegalArgumentException("Algunas categorías no fueron encontradas en la base de datos.");
        }




        List<CategoriaArticuloEntity> categoriaArticuloEntityList = categoriasList.stream().map(categoriaEntity ->
             new CategoriaArticuloEntity(articuloEntity,categoriaEntity)).toList();

        articuloEntity.setCategoriasArticulos(categoriaArticuloEntityList);


        return ArticuloMapper.entityToDomain(articuloCrudRepositoryMySQL.save(articuloEntity));
    }

    public CategoriaEntity actualizarCategoria(CategoriaEntity categoria) {
        // Sincroniza la entidad con el contexto de persistencia
        return entityManager.merge(categoria);
    }



}
