package modela1.reo_comelon_simulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
// 2. Define la ruta base para todos los métodos en esta clase.
@RequestMapping("/api/v1")
public class PruebaController {
    @GetMapping("/saludo")
    public String saludar() {
        // Retorna un String simple. Spring Boot lo convierte en la respuesta HTTP.
        return "¡Hola Comelón! Tu endpoint de Spring Boot esta levantado correctamente.";
    }

    // Opcional: Otro endpoint que retorna un objeto JSON (aunque sea un String).
    // http://localhost:8080/api/v1/status
    @GetMapping("/status")
    public String getStatus() {
        return "{\"status\": \"OK\", \"app_name\": \"reo-comelon-simulator\"}";
    }
}
