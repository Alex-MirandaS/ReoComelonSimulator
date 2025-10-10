package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewRegistroPresosDto;
import modela1.reo_comelon_simulator.dto.response.RegistroPresosDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registroPresos")
public interface RegistroPresosApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createRegistroPresos(@RequestBody NewRegistroPresosDto newRegistroPresosDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllRegistroPresos();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getRegistroPresosById(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateRegistroPresos(@RequestBody RegistroPresosDto registroPresosDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteRegistroPresos(@PathVariable Integer id);
}