package cl.bancosur.api.controller;

import cl.bancosur.api.dto.ClienteRequest;
import cl.bancosur.api.entity.Cliente;
import cl.bancosur.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ok ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Long id) {
        return clienteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)          // 201 en vez de 200
    public Cliente crear(@Valid @RequestBody ClienteRequest request) {
        return clienteService.crear(request);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        return clienteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)       // 204: éxito sin cuerpo
    public void desactivar(@PathVariable Long id) {
        clienteService.desactivar(id);
    }
}
