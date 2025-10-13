package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewSimulacionRequestDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/simulacion")
public interface SimulacionApi {

    @PostMapping("/run")
    ResponseEntity<ResponseSuccessfullyDto> runSimulacion(@RequestBody NewSimulacionRequestDto dto);
}