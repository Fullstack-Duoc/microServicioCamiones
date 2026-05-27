package cl.duoc.mineria.camiones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import cl.duoc.mineria.camiones.dto.CamionRequestDTO;
import cl.duoc.mineria.camiones.dto.CamionResponseDTO;
import cl.duoc.mineria.camiones.service.CamionService;

@RestController

// Ruta base del microservicio
@RequestMapping("/camiones")

public class CamionController {

    // Inyección del service
    @Autowired
    private CamionService camionService;

    // 1. OBTENER TODOS LOS CAMIONES
    @GetMapping
    public ResponseEntity<List<CamionResponseDTO>> getAllCamiones() {
        List<CamionResponseDTO> camiones = camionService.getAllCamiones();
        return ResponseEntity.ok(camiones);
    }

    // 2. OBTENER CAMIÓN POR ID
    @GetMapping("/{id}")
    public ResponseEntity<CamionResponseDTO> getCamionById(@PathVariable Long id) {
        return ResponseEntity.ok(camionService.getById(id));
    }

    // 3. CREAR NUEVO CAMIÓN
    @PostMapping
    public ResponseEntity<CamionResponseDTO> saveCamion(@Valid @RequestBody CamionRequestDTO dto) {
        CamionResponseDTO nuevoCamion = camionService.saveCamion(dto);
        return new ResponseEntity<>(nuevoCamion, HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR CAMIÓN
    @PutMapping("/{id}")
    public ResponseEntity<CamionResponseDTO> updateCamion(@PathVariable Long id, @Valid @RequestBody CamionRequestDTO dto) {
        CamionResponseDTO camionActualizado = camionService.updateCamion(id, dto);
        return ResponseEntity.ok(camionActualizado);
    }

    // 5. ELIMINAR CAMIÓN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamion(@PathVariable Long id) {
        camionService.deleteCamion(id);
        return ResponseEntity.noContent().build();
    }

    // 6. BUSCAR POR CÓDIGO
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CamionResponseDTO> getCamionByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(camionService.getByCodigo(codigo));
    }

    // 7. LISTAR POR ESTADO
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CamionResponseDTO>> getCamionesByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(camionService.getByEstado(estado));
    }

    // 8. LISTAR POR SECTOR
    @GetMapping("/sector/{sector}")
    public ResponseEntity<List<CamionResponseDTO>> getCamionesBySector(@PathVariable String sector) {
        return ResponseEntity.ok(camionService.getBySector(sector));
    }
}