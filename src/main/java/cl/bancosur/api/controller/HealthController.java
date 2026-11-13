package cl.bancosur.api.controller;

import cl.bancosur.api.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    // 1) Declaración: justo al inicio de la clase
    private static final Logger log = LoggerFactory.getLogger(HealthController.class);

    private final InfoService infoService;

    public HealthController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        log.info("Controller: petición recibida en /api/info"); // 2) Llamada
        return infoService.obtenerInfo();
    }
}