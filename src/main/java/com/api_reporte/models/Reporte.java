package com.api_reporte.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "reportes")
@Data
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReporte;

    @Column(nullable = false)
    private String tipoReporte;

    @Column(nullable = false)
    private LocalDate fechaGeneracion;

    @Column(length = 1000)
    private String descripcion;

    @Lob
    @Column(name = "json_datos", columnDefinition = "TEXT")
    private String jsonDatos;
}
