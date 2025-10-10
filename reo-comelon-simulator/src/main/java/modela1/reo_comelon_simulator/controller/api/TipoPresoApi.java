package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.response.TipoPresoDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tipoPreso")
public interface TipoPresoApi {

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllTipoPreso();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getTipoPresoById(@PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteTipoPreso(@PathVariable Integer id);
}