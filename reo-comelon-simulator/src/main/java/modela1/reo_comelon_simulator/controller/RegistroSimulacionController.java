package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRegistroSimulacionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.RegistroSimulacionApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.RegistroSimulacionDto;
import modela1.reo_comelon_simulator.service.RegistroSimulacionService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistroSimulacionController implements RegistroSimulacionApi {
    private final RegistroSimulacionService registroSimulacionService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createRegistroSimulacion(NewRegistroSimulacionDto newRegistroSimulacionDto) {
        log.info("POST /registroSimulacion");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroSimulacionService.createRegistroSimulacion(newRegistroSimulacionDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllRegistroSimulacion() {
        log.info("GET /registroSimulacion/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroSimulacionService.getAllRegistroSimulacion();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getRegistroSimulacionById(Integer id) {
        log.info("GET /registroSimulacion/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroSimulacionService.getRegistroSimulacionById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateRegistroSimulacion(RegistroSimulacionDto registroSimulacionDto) {
        log.info("PUT /registroSimulacion");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroSimulacionService.updateRegistroSimulacion(registroSimulacionDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteRegistroSimulacion(Integer id) {
        log.info("DELETE /registroSimulacion/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroSimulacionService.deleteRegistroSimulacion(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}