package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewCajaIngredientesDto;
import modela1.reo_comelon_simulator.dto.response.CajaIngredientesDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cajaIngredientes")
public interface CajaIngredientesApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createCajaIngredientes(@RequestBody NewCajaIngredientesDto newCajaIngredientesDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllCajaIngredientes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getCajaIngredientesById(@PathVariable Integer id);

    @GetMapping("/ingrediente/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getCajaIngredByIdIngrediente(@PathVariable Integer id);

    @GetMapping("/ocupacionCaja/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getCajaIngredByIdOcupacionCaja(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateCajaIngredientes(@RequestBody CajaIngredientesDto cajaIngredientesDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteCajaIngredientes(@PathVariable Integer id);
}