package com.emazon.stock.infraestructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "supplies_logs")
@Getter
@Setter
@NoArgsConstructor
public class SupplyLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticuloEntity article;

    @Column(name = "supply_id", unique = true)
    private Long supplyId;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;
    @Column(name = "status")
    private String status;
}
