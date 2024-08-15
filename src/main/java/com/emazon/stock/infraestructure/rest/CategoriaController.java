package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.CategoriaService;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import com.emazon.stock.infraestructure.rest.dto.request.CategoriaRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CategoriaResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> guardarCategoria(@RequestBody @Valid CategoriaRequestDTO categoria){
        return new ResponseEntity<>(CategoriaMapper.domainToDto(categoriaService.guardarCategoria(CategoriaMapper.dtoToDomain(categoria))), HttpStatus.OK);
    }


}
