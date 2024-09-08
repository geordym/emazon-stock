package com.emazon.stock.domain.model;

import com.emazon.stock.infraestructure.entities.ArticuloEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class SupplyLog {
    private Long id;
    private Articulo article;
    private Long supplyId;
    private LocalDateTime receivedAt;
    private LocalDateTime confirmedAt;
    private String status;
}
