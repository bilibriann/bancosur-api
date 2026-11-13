package cl.bancosur.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RootController {

    @Value("${bancosur.nombre}")
    private String nombre;

    @Value("${bancosur.version}")
    private String version;

    @GetMapping("/")
    public Map<String, Object> root() {
        return Map.of(
                "mensaje", "Bienvenido a la API de " + nombre,
                "version", version,
                "endpoints", List.of("/api/health", "/api/info", "/api/clientes")
        );
    }
}
