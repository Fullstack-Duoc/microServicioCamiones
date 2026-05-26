package cl.duoc.mineria.camiones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CamionRequestDTO {

    // Código interno del camión
    @NotBlank(message = "El código del camión es obligatorio")
    private String codigo;

    // Modelo del camión
    @NotBlank(message = "El modelo del camión es obligatorio")
    private String modelo;

    // Capacidad de carga
    @NotNull(message = "La capacidad de carga es obligatoria")
    private Double capacidadCarga;

    // Estado operativo permitido
    @NotBlank(message = "El estado operativo es obligatorio")

    @Pattern(
        regexp = "OPERATIVO|MANTENCION|FUERA_SERVICIO",
        message = "Estado inválido. Valores permitidos: OPERATIVO, MANTENCION o FUERA_SERVICIO"
    )

    private String estadoOperativo;

    // Sector de operación
    @NotBlank(message = "El sector es obligatorio")
    private String sector;
}
