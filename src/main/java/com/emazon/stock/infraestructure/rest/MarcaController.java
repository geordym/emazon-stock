package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.MarcaService;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.mapper.MarcaMapper;
import com.emazon.stock.infraestructure.rest.dto.request.MarcaCreateRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.MarcaCreateResponseDTO;
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
    public ResponseEntity<PaginationCustom> listMarcas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {


        PaginationCustom pagination = marcaService.
                listMarcas(new PaginationParams(page, size, sortBy, ascending));

        List<Marca> marcaDomainList = pagination.getContent();
        List<MarcaCreateResponseDTO> marcaDTOList = marcaDomainList.stream()
                .map(MarcaMapper::domainToDto)
                .toList();

        pagination.setContent(marcaDTOList);
        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<MarcaCreateResponseDTO> createMarca(@RequestBody MarcaCreateRequestDTO marcaCreateRequestDTO){
        return new ResponseEntity<>(MarcaMapper.domainToDto(marcaService.saveMarca(MarcaMapper.dtoToDomain(marcaCreateRequestDTO))), HttpStatus.OK);
    }

}
