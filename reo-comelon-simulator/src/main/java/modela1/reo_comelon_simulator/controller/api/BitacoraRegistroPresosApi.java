package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroPresosDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bitacoraRegistroPresos")
public interface BitacoraRegistroPresosApi {
    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllBitacoraRegistroPresos();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getBitacoraRegistroPresosById(@PathVariable Integer id);

    @GetMapping("/registroPresos/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getBitacoraRegistroPresosByIdRegistroPresos(@PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteBitacoraRegistroPresos(@PathVariable Integer id);
}