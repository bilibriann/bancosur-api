package cl.bancosur.api.exception;

// Se lanza cuando se intenta crear algo que ya existe (ej. RUT repetido) → responde 409
public class RecursoDuplicadoException extends RuntimeException {

    public RecursoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
