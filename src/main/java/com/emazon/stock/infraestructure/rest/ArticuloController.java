package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.ArticuloService;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.repositories.ArticuloCrudRepositoryMySQL;
import com.emazon.stock.infraestructure.repositories.CategoriaCrudRepositoryMySQL;
import com.emazon.stock.infraestructure.rest.dto.request.CreateArticuloRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CreateArticuloResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/articulos")
@RequiredArgsConstructor
public class ArticuloController {


    private final ArticuloService articuloService;

    private final CategoriaCrudRepositoryMySQL categoriaCrudRepositoryMySQL;

    private final ArticuloCrudRepositoryMySQL articuloCrudRepositoryMySQL;

    @PostMapping
    public ResponseEntity<CreateArticuloResponseDTO>
    guardarArticulo(@RequestBody CreateArticuloRequestDTO createArticuloRequestDTO){
       return new ResponseEntity<>(
                ArticuloMapper.
                        domainToRequestCreateResponse(
                                articuloService.guardarArticulo(ArticuloMapper.
                                        requestCreateArticuloToDomain
                                        (createArticuloRequestDTO))), HttpStatus.OK );

    }

}
