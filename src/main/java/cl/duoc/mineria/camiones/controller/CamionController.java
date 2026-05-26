package cl.duoc.mineria.camiones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import cl.duoc.mineria.camiones.dto.CamionRequestDTO;
import cl.duoc.mineria.camiones.dto.CamionResponseDTO;
import cl.duoc.mineria.camiones.service.CamionService;

@RestController

// Ruta base del microservicio
@RequestMapping("api/v1/camiones")

public class CamionController {

    // Inyección del service
    @Autowired
    private CamionService camionService;

    // 1. OBTENER TODOS LOS CAMIONES
    @GetMapping
    public List<CamionResponseDTO> getAllCamiones() {

        return camionService.getAllCamiones();
    }

    // 2. OBTENER CAMIÓN POR ID
    @GetMapping("/{id}")

    public CamionResponseDTO getCamionById(
            @PathVariable Long id) {

        return camionService.getById(id);
    }

    // 3. CREAR NUEVO CAMIÓN
    @PostMapping

    // Devuelve HTTP 201 Created
    @ResponseStatus(HttpStatus.CREATED)

    public CamionResponseDTO saveCamion(

            // Activa validaciones
            @Valid @RequestBody CamionRequestDTO dto) {

        return camionService.saveCamion(dto);
    }

    // 4. ACTUALIZAR CAMIÓN
    @PutMapping("/{id}")

    public CamionResponseDTO updateCamion(
            @PathVariable Long id,

            @Valid @RequestBody CamionRequestDTO dto) {

        return camionService.updateCamion(id, dto);
    }

    // 5. ELIMINAR CAMIÓN
    @DeleteMapping("/{id}")

    public String deleteCamion(
            @PathVariable Long id) {

        camionService.deleteCamion(id);

        return "Camión con id "
                + id
                + " eliminado correctamente";
    }
}