package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewRegistroComprasDto;
import modela1.reo_comelon_simulator.dto.response.RegistroComprasDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registroCompras")
public interface RegistroComprasApi {

    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createRegistroCompras(@RequestBody NewRegistroComprasDto dto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllRegistroCompras();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getRegistroComprasById(@PathVariable Integer id);

    @GetMapping("/simulacion/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getByIdSimulacion(@PathVariable Integer id);

    @GetMapping("/cajaIngredientes/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getByIdCajaIngredientes(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateRegistroCompras(@RequestBody RegistroComprasDto dto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteRegistroCompras(@PathVariable Integer id);
}
