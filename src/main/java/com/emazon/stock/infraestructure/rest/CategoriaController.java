package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.CategoriaService;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import com.emazon.stock.infraestructure.rest.dto.response.CategoriaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {


        // Llamar al servicio para obtener la lista de categorías
        List<Categoria> categorias = categoriaService.listarCategorias(page,size,sortBy,ascending);

        // Mapear a DTOs para la respuesta
        List<CategoriaResponseDTO> categoriaResponseDTOs = categorias.stream()
                .map(CategoriaMapper::domainToDto)
                .toList();

        return new ResponseEntity<>(categoriaResponseDTOs, HttpStatus.OK);
    }

}
