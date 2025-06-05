package com.api_reporte.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReporteDTO {
    private Integer idReporte;
    private String tipoReporte;
    private LocalDate fechaGeneracion;
    private String descripcion;
    private String jsonDatos;
}
