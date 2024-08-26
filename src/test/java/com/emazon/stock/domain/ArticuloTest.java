package com.emazon.stock.domain;


import com.emazon.stock.application.services.ArticuloService;
import com.emazon.stock.application.usecases.CrearArticuloUseCaseImpl;
import com.emazon.stock.application.usecases.ListArticlesUseCaseImpl;
import com.emazon.stock.domain.exception.ArticuloCategoriaRepetidaException;
import com.emazon.stock.domain.exception.ArticuloConExcesoCategorias;
import com.emazon.stock.domain.exception.ArticuloConFaltaDeCategorias;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.model.CategoriaArticulo;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.emazon.stock.domain.util.Constantes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ArticuloTest {

    Categoria categoria1 = new Categoria(1L, "a".repeat(LONGITUD_NOMBRE_MAXIMA), "a".repeat(LONGITUD_DESCRIPCION_MAXIMA));
    Categoria categoria2 = new Categoria(1L, "a".repeat(LONGITUD_NOMBRE_MAXIMA), "a".repeat(LONGITUD_DESCRIPCION_MAXIMA));
    Categoria categoria3 = new Categoria(1L, "a".repeat(LONGITUD_NOMBRE_MAXIMA), "a".repeat(LONGITUD_DESCRIPCION_MAXIMA));
    Categoria categoria4 = new Categoria(1L, "a".repeat(LONGITUD_NOMBRE_MAXIMA), "a".repeat(LONGITUD_DESCRIPCION_MAXIMA));

    List<CategoriaArticulo> categoriaArticuloList = new ArrayList<>();
    Articulo articulo = new Articulo(1L, "", "", 1, 10, new ArrayList<>());

    ArticuloService articuloService;
    ArticuloRepositoryPort articuloRepositoryPort;
    CrearArticuloUseCaseImpl crearArticuloUseCase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        articuloRepositoryPort = Mockito.mock(ArticuloRepositoryPort.class);
        crearArticuloUseCase = new CrearArticuloUseCaseImpl(articuloRepositoryPort);
        ListArticlesUseCaseImpl listarArticuloUseCase = new ListArticlesUseCaseImpl(articuloRepositoryPort);
        articuloService = new ArticuloService(crearArticuloUseCase, listarArticuloUseCase);

        CategoriaArticulo categoriaArticulo = new CategoriaArticulo(articulo,categoria1);
        categoriaArticuloList.add(categoriaArticulo);
        articulo.setCategorias(categoriaArticuloList);
    }



    @Test
     void testGuardarCategoriaExitoso() {
        when(articuloRepositoryPort.guardarArticulo(articulo)).thenReturn(articulo);
        Articulo resultado = articuloService.guardarArticulo(articulo);
        assertNotNull(resultado);
        assertEquals(articulo, resultado);
        verify(articuloRepositoryPort).guardarArticulo(articulo);
    }

    @Test
     void testCrearArticuloSinCategoriaLanzaExcepcion() {
        articulo.setCategorias(new ArrayList<>());
        assertThrows(ArticuloConFaltaDeCategorias.class, () -> {
           articuloService.guardarArticulo(articulo);
        });
    }

    @Test
    void testCrearArticuloConExcesoCategoriasLanzaExcepcion() {
        CategoriaArticulo categoriaArticulo1 = new CategoriaArticulo(articulo,categoria1);
        CategoriaArticulo categoriaArticulo2 = new CategoriaArticulo(articulo,categoria2);
        CategoriaArticulo categoriaArticulo3 = new CategoriaArticulo(articulo,categoria3);
        CategoriaArticulo categoriaArticulo4 = new CategoriaArticulo(articulo,categoria4);
        categoriaArticuloList = new ArrayList<>();
        categoriaArticuloList.add(categoriaArticulo1);
        categoriaArticuloList.add(categoriaArticulo2);
        categoriaArticuloList.add(categoriaArticulo3);
        categoriaArticuloList.add(categoriaArticulo4);

        articulo.setCategorias(categoriaArticuloList);
        assertThrows(ArticuloConExcesoCategorias.class, () -> {
            articuloService.guardarArticulo(articulo);
        });
    }

    @Test
    void testCrearArticuloConCategoriasRepetidasLanzaExcepcion() {
        List<CategoriaArticulo> categoriaArticuloList = crearCategoriasArticulos(ARTICULO_MAXIMO_CATEGORIAS);
        articulo.setCategorias(categoriaArticuloList);
        assertThrows(ArticuloCategoriaRepetidaException.class, () -> {
            articuloService.guardarArticulo(articulo);
        });
    }

    private List<CategoriaArticulo> crearCategoriasArticulos(int maxCategorias) {
        List<CategoriaArticulo> lista = new ArrayList<>();

        for (int i = 1; i <= maxCategorias; i++) {
            // Crear instancias de CategoriaArticulo con diferentes categorías
            CategoriaArticulo categoriaArticulo = new CategoriaArticulo(articulo, categoria1);
            lista.add(categoriaArticulo);
        }

        return lista;
    }









}
