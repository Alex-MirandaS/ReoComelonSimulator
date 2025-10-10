package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.response.TipoRecetaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tipoReceta")
public interface TipoRecetaApi {

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllTipoReceta();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getTipoRecetaById(@PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteTipoReceta(@PathVariable Integer id);
}