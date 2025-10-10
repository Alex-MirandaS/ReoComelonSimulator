package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewRegistroSimulacionDto;
import modela1.reo_comelon_simulator.dto.response.RegistroSimulacionDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registroSimulacion")
public interface RegistroSimulacionApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createRegistroSimulacion(@RequestBody NewRegistroSimulacionDto newRegistroSimulacionDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllRegistroSimulacion();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getRegistroSimulacionById(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateRegistroSimulacion(@RequestBody RegistroSimulacionDto registroSimulacionDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteRegistroSimulacion(@PathVariable Integer id);
}