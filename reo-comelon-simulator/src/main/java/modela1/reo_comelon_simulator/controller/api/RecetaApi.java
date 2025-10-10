package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewRecetaDto;
import modela1.reo_comelon_simulator.dto.response.RecetaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/receta")
public interface RecetaApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createReceta(@RequestBody NewRecetaDto newRecetaDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllReceta();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getRecetaById(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateReceta(@RequestBody RecetaDto recetaDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteReceta(@PathVariable Integer id);
}