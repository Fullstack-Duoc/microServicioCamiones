package cl.duoc.mineria.camiones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.duoc.mineria.camiones.dto.CamionRequestDTO;
import cl.duoc.mineria.camiones.dto.CamionResponseDTO;
import cl.duoc.mineria.camiones.exception.ResourceNotFoundException;
import cl.duoc.mineria.camiones.mapper.CamionMapper;
import cl.duoc.mineria.camiones.model.Camion;
import cl.duoc.mineria.camiones.repository.CamionRepository;

@Service
public class CamionService {

    // Inyección del repository
    @Autowired
    private CamionRepository camionRepository;

    // Inyección del mapper
    @Autowired
    private CamionMapper camionMapper;

    // 1. OBTENER TODOS LOS CAMIONES
    public List<CamionResponseDTO> getAllCamiones() {

        return camionRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))

                // Convierte lista a Stream
                .stream()

                // Convierte Entity -> DTO
                .map(camionMapper::toResponseDTO)

                // Convierte nuevamente a lista
                .toList();
    }

    // 2. OBTENER CAMIÓN POR ID
    public CamionResponseDTO getById(Long id) {

        Camion camion = camionRepository.findById(id)

                // Error si no existe
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Camión no encontrado con el ID: " + id));

        return camionMapper.toResponseDTO(camion);
    }

    // 3. GUARDAR NUEVO CAMIÓN
    public CamionResponseDTO saveCamion(CamionRequestDTO dto) {

        // Evita códigos duplicados
        if (camionRepository.findByCodigo(dto.getCodigo()).isPresent()) {

            throw new IllegalArgumentException(
                    "El código '" + dto.getCodigo()
                            + "' ya se encuentra registrado.");
        }

        // DTO -> Entity
        Camion camion = camionMapper.toEntity(dto);

        // Guarda en PostgreSQL
        Camion camionGuardado =
                camionRepository.save(camion);

        // Entity -> DTO
        return camionMapper.toResponseDTO(camionGuardado);
    }

    // 4. ELIMINAR CAMIÓN
    public void deleteCamion(Long id) {

        // Verifica existencia
        if (!camionRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "No se puede eliminar. Camión no encontrado con el ID: "
                            + id);
        }

        camionRepository.deleteById(id);
    }

    // 5. ACTUALIZAR CAMIÓN
    public CamionResponseDTO updateCamion(
            Long id,
            CamionRequestDTO dto) {

        // Busca camión existente
        Camion camionExistente =
                camionRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No se puede actualizar. Camión no encontrado con el ID: "
                                                + id));

        // Actualiza atributos
        camionExistente.setCodigo(dto.getCodigo());

        camionExistente.setModelo(dto.getModelo());

        camionExistente.setCapacidadCarga(dto.getCapacidadCarga());

        camionExistente.setEstadoOperativo(dto.getEstadoOperativo());

        camionExistente.setSector(dto.getSector());

        // Guarda cambios
        Camion camionActualizado =
                camionRepository.save(camionExistente);

        return camionMapper.toResponseDTO(camionActualizado);
    }
}
