package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewOcupacionCajaDto;
import modela1.reo_comelon_simulator.dto.response.OcupacionCajaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ocupacionCaja")
public interface OcupacionCajaApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createOcupacionCaja(@RequestBody NewOcupacionCajaDto newOcupacionCajaDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllOcupacionCaja();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getOcupacionCajaById(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateOcupacionCaja(@RequestBody OcupacionCajaDto ocupacionCajaDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteOcupacionCaja(@PathVariable Integer id);
}