package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewRecetaIngredienteDto;
import modela1.reo_comelon_simulator.dto.response.RecetaIngredienteDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recetaIngrediente")
public interface RecetaIngredienteApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createRecetaIngrediente(@RequestBody NewRecetaIngredienteDto newRecetaIngredienteDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllRecetaIngrediente();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getRecetaIngredienteById(@PathVariable Integer id);

    @GetMapping("/receta/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getRecetaIngredByIdReceta(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateRecetaIngrediente(@RequestBody RecetaIngredienteDto recetaIngredienteDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteRecetaIngrediente(@PathVariable Integer id);
}