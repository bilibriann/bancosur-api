package cl.bancosur.api.service;

import cl.bancosur.api.dto.ClienteRequest;
import cl.bancosur.api.entity.Cliente;
import cl.bancosur.api.exception.RecursoDuplicadoException;
import cl.bancosur.api.exception.RecursoNoEncontradoException;
import cl.bancosur.api.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente obtener(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un cliente con id " + id));
    }

    @Transactional
    public Cliente crear(ClienteRequest request) {
        String rut = request.rut().toUpperCase();
        if (clienteRepository.existsByRut(rut)) {
            throw new RecursoDuplicadoException("Ya existe un cliente con RUT " + rut);
        }
        if (clienteRepository.existsByEmail(request.email())) {
            throw new RecursoDuplicadoException("Ya existe un cliente con email " + request.email());
        }

        Cliente cliente = new Cliente();
        cliente.setRut(rut);
        cliente.setNombre(request.nombre());
        cliente.setEmail(request.email());

        Cliente guardado = clienteRepository.save(cliente);
        log.info("Service: cliente creado con id {}", guardado.getId());
        return guardado;
    }

    // El RUT no se cambia: identifica a la persona
    @Transactional
    public Cliente actualizar(Long id, ClienteRequest request) {
        Cliente cliente = obtener(id);
        if (clienteRepository.existsByEmailAndIdNot(request.email(), id)) {
            throw new RecursoDuplicadoException("Ya existe otro cliente con email " + request.email());
        }
        cliente.setNombre(request.nombre());
        cliente.setEmail(request.email());
        return clienteRepository.save(cliente);
    }

    // En un banco no se borran clientes: se desactivan
    @Transactional
    public void desactivar(Long id) {
        Cliente cliente = obtener(id);
        cliente.setActivo(false);
        clienteRepository.save(cliente);
        log.info("Service: cliente {} desactivado", id);
    }
}
