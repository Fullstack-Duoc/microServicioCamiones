package cl.duoc.mineria.camiones.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import cl.duoc.mineria.camiones.dto.CamionRequestDTO;
import cl.duoc.mineria.camiones.dto.CamionResponseDTO;
import cl.duoc.mineria.camiones.model.Camion;

@Component
public class CamionMapper {

    // Convierte RequestDTO -> Entity
    public Camion toEntity(CamionRequestDTO dto) {

        // Validación para evitar null
        if (dto == null) {
            return null;
        }

        Camion camion = new Camion();

        camion.setCodigo(dto.getCodigo());

        camion.setModelo(dto.getModelo());

        camion.setCapacidadCarga(dto.getCapacidadCarga());

        camion.setEstadoOperativo(dto.getEstadoOperativo());

        camion.setSector(dto.getSector());

        // Fecha automática de registro
        camion.setFechaRegistro(LocalDateTime.now());

        return camion;
    }

    // Convierte Entity -> ResponseDTO
    public CamionResponseDTO toResponseDTO(Camion camion) {

        // Validación null
        if (camion == null) {
            return null;
        }

        CamionResponseDTO dto = new CamionResponseDTO();

        dto.setId(camion.getId());

        dto.setCodigo(camion.getCodigo());

        dto.setModelo(camion.getModelo());

        dto.setCapacidadCarga(camion.getCapacidadCarga());

        dto.setEstadoOperativo(camion.getEstadoOperativo());

        dto.setSector(camion.getSector());

        return dto;
    }
}
