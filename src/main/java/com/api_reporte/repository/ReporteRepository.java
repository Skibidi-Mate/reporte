package com.api_reporte.repository;

import com.api_reporte.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
}
