package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewSimulacionMenusDto;
import modela1.reo_comelon_simulator.dto.response.SimulacionMenusDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/simulacionMenus")
public interface SimulacionMenusApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createSimulacionMenus(@RequestBody NewSimulacionMenusDto newSimulacionMenusDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllSimulacionMenus();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getSimulacionMenusById(@PathVariable Integer id);

    @GetMapping("/simulacion/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getSimulacionMenuByIdSimulacion(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateSimulacionMenus(@RequestBody SimulacionMenusDto simulacionMenusDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteSimulacionMenus(@PathVariable Integer id);
}