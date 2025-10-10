package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewSimulacionMenusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.SimulacionMenusApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.SimulacionMenusDto;
import modela1.reo_comelon_simulator.service.SimulacionMenusService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SimulacionMenusController implements SimulacionMenusApi {
    private final SimulacionMenusService simulacionMenusService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createSimulacionMenus(NewSimulacionMenusDto newSimulacionMenusDto) {
        log.info("POST /simulacionMenus");
        ResponseSuccessfullyDto responseSuccessfullyDto = simulacionMenusService.createSimulacionMenus(newSimulacionMenusDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllSimulacionMenus() {
        log.info("GET /simulacionMenus/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = simulacionMenusService.getAllSimulacionMenus();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getSimulacionMenusById(Integer id) {
        log.info("GET /simulacionMenus/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = simulacionMenusService.getSimulacionMenusById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateSimulacionMenus(SimulacionMenusDto simulacionMenusDto) {
        log.info("PUT /simulacionMenus");
        ResponseSuccessfullyDto responseSuccessfullyDto = simulacionMenusService.updateSimulacionMenus(simulacionMenusDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteSimulacionMenus(Integer id) {
        log.info("DELETE /simulacionMenus/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = simulacionMenusService.deleteSimulacionMenus(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}