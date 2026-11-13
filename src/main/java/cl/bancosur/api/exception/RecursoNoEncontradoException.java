package cl.bancosur.api.exception;

// Se lanza cuando se busca algo que no existe → responde 404
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
