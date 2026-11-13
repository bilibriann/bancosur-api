package cl.bancosur.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

// Convierte las excepciones de todos los controllers en respuestas JSON claras
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> noEncontrado(RecursoNoEncontradoException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> duplicado(RecursoDuplicadoException ex) {
        return Map.of("error", ex.getMessage());
    }

    // Errores de @Valid: devuelve qué campo falló y por qué
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> validacion(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> campos.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return Map.of("error", "Datos inválidos", "campos", campos);
    }
}
