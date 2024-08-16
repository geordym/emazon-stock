package com.emazon.stock.application.usecases;

import com.emazon.stock.domain.exception.ArticuloCategoriaRepetidaException;
import com.emazon.stock.domain.exception.ArticuloConExcesoCategorias;
import com.emazon.stock.domain.exception.ArticuloConFaltaDeCategorias;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.CategoriaArticulo;
import com.emazon.stock.domain.puertos.in.CrearArticuloUseCase;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.emazon.stock.domain.util.Constantes.ARTICULO_MAXIMO_CATEGORIAS;
import static com.emazon.stock.domain.util.Constantes.ARTICULO_MINIMO_CATEGORIAS;


@RequiredArgsConstructor
public class CrearArticuloUseCaseImpl implements CrearArticuloUseCase {


    private final ArticuloRepositoryPort articuloRepositoryPort;

    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        comprobarMinimoUnaCategoria(articulo);
        comprobarMaximoTresCategorias(articulo);
        comprobarCategoriaRepetida(articulo);
        return articuloRepositoryPort.guardarArticulo(articulo);
    }


    private void comprobarMaximoTresCategorias(Articulo articulo){
        List<CategoriaArticulo> categoriaArticuloList = articulo.getCategorias();
        if(categoriaArticuloList.size() > ARTICULO_MAXIMO_CATEGORIAS){
            throw new ArticuloConExcesoCategorias("Se permiten maximo "+ ARTICULO_MAXIMO_CATEGORIAS +" categorias");
        }
    }

    private void comprobarMinimoUnaCategoria(Articulo articulo){
        List<CategoriaArticulo> categoriaArticuloList = articulo.getCategorias();
        List<Long> categoriaArticulosIdList = categoriaArticuloList.stream().map(categoria -> categoria.getCategoria().getIdCategoria()).toList();

        if(categoriaArticulosIdList.size() < ARTICULO_MINIMO_CATEGORIAS){
            throw new ArticuloConFaltaDeCategorias("Debe tener minimo " + ARTICULO_MINIMO_CATEGORIAS +  " categoria");
        }
    }

    public static void comprobarCategoriaRepetida(Articulo articulo) {

        List<CategoriaArticulo> categoriaArticuloList = articulo.getCategorias() ;
        List<Long> categoriaArticulosIdList = categoriaArticuloList.stream().map(categoria -> categoria.getCategoria().getIdCategoria()).toList();

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
