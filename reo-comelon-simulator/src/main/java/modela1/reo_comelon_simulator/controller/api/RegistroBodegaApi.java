package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewRegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.RegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registroBodega")
public interface RegistroBodegaApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createRegistroBodega(@RequestBody NewRegistroBodegaDto newRegistroBodegaDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllRegistroBodega();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getRegistroBodegaById(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateRegistroBodega(@RequestBody RegistroBodegaDto registroBodegaDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteRegistroBodega(@PathVariable Integer id);
}