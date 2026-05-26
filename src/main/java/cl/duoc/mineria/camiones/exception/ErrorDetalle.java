package cl.duoc.mineria.camiones.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase que representa errores JSON personalizados
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ErrorDetalle {

    // Fecha y hora del error
    private LocalDateTime timestamp;

    // Mensaje amigable
    private String mensaje;

    // Ruta o detalles adicionales
    private String detalles;
}