package com.api_reporte.controller;

import com.api_reporte.dto.ReporteDTO;
import com.api_reporte.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService service;

    @PostMapping
    public ResponseEntity<EntityModel<ReporteDTO>> crear(@RequestBody ReporteDTO dto) {
        ReporteDTO creado = service.guardar(dto);
        EntityModel<ReporteDTO> resource = EntityModel.of(creado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReporteController.class).obtener(creado.getIdReporte())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReporteController.class).listar()).withRel("reportes")
        );
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ReporteDTO>>> listar() {
        List<EntityModel<ReporteDTO>> recursos = service.listar().stream()
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReporteController.class).obtener(dto.getIdReporte())).withSelfRel()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReporteDTO>> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReporteController.class).obtener(id)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReporteController.class).listar()).withRel("reportes")
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ReporteDTO>> actualizar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        return service.actualizar(id, dto)
                .map(updated -> EntityModel.of(updated,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReporteController.class).obtener(id)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReporteController.class).listar()).withRel("reportes")
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
