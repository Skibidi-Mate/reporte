package com.api_reporte.service;

import com.api_reporte.dto.ReporteDTO;
import com.api_reporte.models.Reporte;
import com.api_reporte.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository repository;

    public ReporteDTO guardar(ReporteDTO dto) {
        Reporte reporte = toEntity(dto);
        Reporte guardado = repository.save(reporte);
        return toDTO(guardado);
    }

    public List<ReporteDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReporteDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<ReporteDTO> actualizar(Integer id, ReporteDTO dto) {
        return repository.findById(id).map(reporte -> {
            reporte.setTipoReporte(dto.getTipoReporte());
            reporte.setFechaGeneracion(dto.getFechaGeneracion());
            reporte.setDescripcion(dto.getDescripcion());
            reporte.setJsonDatos(dto.getJsonDatos());
            return toDTO(repository.save(reporte));
        });
    }

    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Conversión
    private ReporteDTO toDTO(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setIdReporte(reporte.getIdReporte());
        dto.setTipoReporte(reporte.getTipoReporte());
        dto.setFechaGeneracion(reporte.getFechaGeneracion());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setJsonDatos(reporte.getJsonDatos());
        return dto;
    }

    private Reporte toEntity(ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setIdReporte(dto.getIdReporte());
        reporte.setTipoReporte(dto.getTipoReporte());
        reporte.setFechaGeneracion(dto.getFechaGeneracion());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setJsonDatos(dto.getJsonDatos());
        return reporte;
    }
}
