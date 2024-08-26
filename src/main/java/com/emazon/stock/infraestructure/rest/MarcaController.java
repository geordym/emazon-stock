package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.MarcaService;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.infraestructure.mapper.MarcaMapper;
import com.emazon.stock.infraestructure.rest.dto.request.MarcaRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.MarcaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> listMarcas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {


        List<Marca> marcas = marcaService.listMarcas(page,size,sortBy,ascending);

        List<MarcaResponseDTO> marcaResponseDTOS = marcas.stream()
                .map(MarcaMapper::domainToDto)
                .toList();

        return new ResponseEntity<>(marcaResponseDTOS, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<MarcaResponseDTO> createMark(@RequestBody MarcaRequestDTO marcaRequestDTO){
        return new ResponseEntity<>(MarcaMapper.domainToDto(marcaService.saveMarca(MarcaMapper.dtoToDomain(marcaRequestDTO))), HttpStatus.OK);
    }

}
