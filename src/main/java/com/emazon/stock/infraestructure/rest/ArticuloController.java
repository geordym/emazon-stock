package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.ArticuloService;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import com.emazon.stock.infraestructure.repositories.ArticuloCrudRepositoryMySQL;
import com.emazon.stock.infraestructure.repositories.CategoriaCrudRepositoryMySQL;
import com.emazon.stock.infraestructure.rest.dto.request.CreateArticuloRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.ArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CategoriaResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CreateArticuloResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/articulos")
@RequiredArgsConstructor
public class ArticuloController {


    private final ArticuloService articuloService;

    private final CategoriaCrudRepositoryMySQL categoriaCrudRepositoryMySQL;

    private final ArticuloCrudRepositoryMySQL articuloCrudRepositoryMySQL;


    @GetMapping
    public ResponseEntity<List<ArticuloResponseDTO>> listarArticulos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {


        // Llamar al servicio para obtener la lista de categorías
        List<Articulo> categorias = articuloService.listarArticulos(new PaginationParams(page,size,sortBy,ascending));

        // Mapear a DTOs para la respuesta
        List<CategoriaResponseDTO> categoriaResponseDTOs = categorias.stream()
                .map(CategoriaMapper::domainToDto)
                .toList();

        return new ResponseEntity<>(categoriaResponseDTOs, HttpStatus.OK);
    }

}
