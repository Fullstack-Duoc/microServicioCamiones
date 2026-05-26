package cl.duoc.mineria.camiones.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Configura automáticamente HTTP 404
@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {

    // Constructor personalizado
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
