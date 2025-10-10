package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroOcupacionCajaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bitacoraRegistroOcupacionCaja")
public interface BitacoraRegistroOcupacionCajaApi {

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllBitacoraRegistroOcupacionCaja();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getBitacoraRegistroOcupacionCajaById(@PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteBitacoraRegistroOcupacionCaja(@PathVariable Integer id);
}