package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.controller.api.SimulacionApi;
import modela1.reo_comelon_simulator.dto.request.NewSimulacionRequestDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.service.SimulacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SimulacionController implements SimulacionApi {

    private final SimulacionService simulacionService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> runSimulacion(NewSimulacionRequestDto dto) {
        log.info("POST /simulacion/run");
        ResponseSuccessfullyDto response = simulacionService.runSimulacion(dto);
        return new ResponseEntity<>(response, response.getCode());
    }
}
