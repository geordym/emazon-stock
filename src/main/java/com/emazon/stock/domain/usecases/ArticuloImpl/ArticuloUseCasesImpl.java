package com.emazon.stock.domain.usecases.ArticuloImpl;

import com.emazon.stock.domain.exception.ArticuloCategoriaRepetidaException;
import com.emazon.stock.domain.exception.ArticuloConExcesoCategoriasException;
import com.emazon.stock.domain.exception.ArticuloConFaltaDeCategoriasException;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.in.ArticuloUseCases;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.github.javafaker.Cat;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.emazon.stock.domain.util.Constantes.ARTICULO_MAXIMO_CATEGORIAS;
import static com.emazon.stock.domain.util.Constantes.ARTICULO_MINIMO_CATEGORIAS;


@RequiredArgsConstructor
public class ArticuloUseCasesImpl implements ArticuloUseCases {


    private final ArticuloRepositoryPort articuloRepositoryPort;


    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {
        return articuloRepositoryPort.listArticles(paginationParams);
    }
    @Override
    public Articulo saveArticulo(Articulo articulo) {
        comprobarMinimoUnaCategoria(articulo);
        comprobarMaximoTresCategorias(articulo);
        comprobarCategoriaRepetida(articulo);
        return articuloRepositoryPort.saveArticulo(articulo);
    }


    private void comprobarMaximoTresCategorias(Articulo articulo){
        List<Category> categoriaArticuloList = articulo.getCategories();
        if(categoriaArticuloList.size() > ARTICULO_MAXIMO_CATEGORIAS){
            throw new ArticuloConExcesoCategoriasException("Se permiten maximo "+ ARTICULO_MAXIMO_CATEGORIAS +" categorias");
        }
    }

    private void comprobarMinimoUnaCategoria(Articulo articulo){
        List<Category> categoriaArticuloList = articulo.getCategories();
        List<Long> categoriaArticulosIdList = categoriaArticuloList.stream().map(categoria -> categoria.getId_categoria()).toList();

        if(categoriaArticulosIdList.size() < ARTICULO_MINIMO_CATEGORIAS){
            throw new ArticuloConFaltaDeCategoriasException("Debe tener minimo " + ARTICULO_MINIMO_CATEGORIAS +  " categoria");
        }
    }

    public static void comprobarCategoriaRepetida(Articulo articulo) {

        List<Category> categoriaArticuloList = articulo.getCategories() ;
        List<Long> categoriaArticulosIdList = categoriaArticuloList.stream().map(categoria -> categoria.getId_categoria()).toList();

        // Usar un Set para detectar duplicados
        Set<Long> categoriaArticulosIdSet = new HashSet<>();

        for (Long id : categoriaArticulosIdList) {
            if (!categoriaArticulosIdSet.add(id)) {
                // Si add() devuelve false, significa que el ID ya estaba en el Set (duplicado)
                throw new ArticuloCategoriaRepetidaException("Hay categorías repetidas, no se puede hacer esto");
            }
        }


    }
}
