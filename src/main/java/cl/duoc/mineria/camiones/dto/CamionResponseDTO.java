package cl.duoc.mineria.camiones.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CamionResponseDTO {

    // ID del camión
    private Long id;

    // Código interno
    private String codigo;

    // Modelo del camión
    private String modelo;

    // Capacidad de carga
    private Double capacidadCarga;

    // Estado operativo
    private String estadoOperativo;

    // Sector asignado
    private String sector;
}