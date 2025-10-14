package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewBitacoraRegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bitacoraBodega")
public interface BitacoraRegistroBodegaApi {

    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createBitacora(@RequestBody NewBitacoraRegistroBodegaDto dto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllBitacora();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getBitacoraById(@PathVariable Integer id);

    @GetMapping("/bodega/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getByIdBodega(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateBitacora(@RequestBody BitacoraRegistroBodegaDto dto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteBitacora(@PathVariable Integer id);
}

