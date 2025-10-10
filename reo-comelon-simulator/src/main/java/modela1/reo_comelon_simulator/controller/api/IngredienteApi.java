package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewIngredienteDto;
import modela1.reo_comelon_simulator.dto.response.IngredienteDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ingrediente")
public interface IngredienteApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createIngrediente(@RequestBody NewIngredienteDto newIngredienteDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllIngrediente();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getIngredienteById(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateIngrediente(@RequestBody IngredienteDto ingredienteDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteIngrediente(@PathVariable Integer id);
}