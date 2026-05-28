package cl.duoc.mineria.camiones.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. MANEJO DE RECURSOS NO ENCONTRADOS
    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<ErrorDetalle> manejarResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest) {

        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(exception.getMessage())
                .detalles(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetalle, HttpStatus.NOT_FOUND);
    }

    // 2. MANEJO DE ERRORES DE VALIDACIÓN
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarValidaciones(
            MethodArgumentNotValidException exception,
            WebRequest webRequest) {
        Map<String, String> erroresCampos = new HashMap<>();
        // Recorre todos los errores
        exception.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String nombreCampo = ((FieldError) error).getField();
                    String mensajeError = error.getDefaultMessage();
                    erroresCampos.put(nombreCampo, mensajeError);
                });

        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Los datos enviados no son válidos")
                .detalles(erroresCampos.toString())
                .build();

        return new ResponseEntity<>(errorDetalle, HttpStatus.BAD_REQUEST);
    }

    // 3. MANEJO DE ARGUMENTOS ILEGALES (Ej: Código duplicado)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetalle> manejarIllegalArgumentException(
            IllegalArgumentException exception,
            WebRequest webRequest) {

        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Error en la solicitud: " + exception.getMessage())
                .detalles(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetalle, HttpStatus.BAD_REQUEST);
    }

    // 4. MANEJO DE ERRORES GENERALES
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalle> manejarGlobalException(
            Exception exception,
            WebRequest webRequest) {

        ErrorDetalle errorDetalle = ErrorDetalle.builder()

                .timestamp(LocalDateTime.now())
                .mensaje("Ocurrió un error interno en el servidor: " + exception.getMessage())
                .detalles(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetalle, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
