package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.MarcaService;
import com.emazon.stock.infraestructure.mapper.MarcaMapper;
import com.emazon.stock.infraestructure.rest.dto.request.MarcaRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.MarcaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> crearMarca(@RequestBody MarcaRequestDTO marcaRequestDTO){
        return new ResponseEntity<>(MarcaMapper.domainToDto(marcaService.guardarMarca(MarcaMapper.dtoToDomain(marcaRequestDTO))), HttpStatus.OK);
    }

}
