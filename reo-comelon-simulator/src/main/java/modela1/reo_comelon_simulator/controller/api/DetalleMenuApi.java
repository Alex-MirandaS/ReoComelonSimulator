package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewDetalleMenuDto;
import modela1.reo_comelon_simulator.dto.response.DetalleMenuDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/detalleMenu")
public interface DetalleMenuApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createDetalleMenu(@RequestBody NewDetalleMenuDto newDetalleMenuDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllDetalleMenu();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getDetalleMenuById(@PathVariable Integer id);

    @GetMapping("/menu/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getDetalleMenuByIdMenu(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateDetalleMenu(@RequestBody DetalleMenuDto detalleMenuDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteDetalleMenu(@PathVariable Integer id);
}