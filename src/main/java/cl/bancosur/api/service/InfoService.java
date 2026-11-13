package cl.bancosur.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class InfoService {

    // 1) Declaración
    private static final Logger log = LoggerFactory.getLogger(InfoService.class);

    @Value("${bancosur.nombre}")
    private String nombre;

    @Value("${bancosur.version}")
    private String version;

    @Value("${bancosur.ambiente}")
    private String ambiente;

    public Map<String, String> obtenerInfo() {
        log.info("Service: armando información del banco"); // 2) Llamada
        return Map.of(
                "banco", nombre,
                "version", version,
                "ambiente", ambiente,
                "fecha", LocalDate.now().toString()
        );
    }
}