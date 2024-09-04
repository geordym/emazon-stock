package com.emazon.stock.domain.usecases.ArticuloImpl;

import com.emazon.stock.domain.exception.ArticuloCategoriaRepetidaException;
import com.emazon.stock.domain.exception.ArticuloConExcesoCategoriasException;
import com.emazon.stock.domain.exception.ArticuloConFaltaDeCategoriasException;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.in.ArticuloUseCases;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.usecases.ArticuloImpl.validators.ArticuloValidator;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.github.javafaker.Cat;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.emazon.stock.domain.util.Constantes.ARTICULO_MAXIMO_CATEGORIAS;
import static com.emazon.stock.domain.util.Constantes.ARTICULO_MINIMO_CATEGORIAS;


@RequiredArgsConstructor
public class ArticuloUseCasesImpl implements ArticuloUseCases {


    private final ArticuloRepositoryPort articuloRepositoryPort;
    private final ArticuloValidator articuloValidator;

    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {
        return articuloRepositoryPort.listArticles(paginationParams);
    }

    @Override
    public Optional<Articulo> findArticleById(Long articleId) {
        return articuloRepositoryPort.findArticleById(articleId);
    }

    @Override
    public void updateArticleStock(Long articleId, int quantity) {
        articuloRepositoryPort.updateArticleStock(articleId, quantity);
    }

    @Override
    public Articulo saveArticulo(Articulo articulo) {
        articuloValidator.saveArticleValidate(articulo);
        return articuloRepositoryPort.saveArticulo(articulo);
    }

}
