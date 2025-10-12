package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRegistroPresosDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.RegistroPresosApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.RegistroPresosDto;
import modela1.reo_comelon_simulator.service.RegistroPresosService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistroPresosController implements RegistroPresosApi {
    private final RegistroPresosService registroPresosService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createRegistroPresos(NewRegistroPresosDto newRegistroPresosDto) {
        log.info("POST /registroPresos");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroPresosService.createRegistroPresos(newRegistroPresosDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllRegistroPresos() {
        log.info("GET /registroPresos/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroPresosService.getAllRegistroPresos();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getRegistroPresosById(Integer id) {
        log.info("GET /registroPresos/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroPresosService.getRegistroPresosById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getRegistroPresosByIdTipoPreso(Integer id) {
        log.info("GET /registroPresos/tipoPreso/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroPresosService.getRegistroPresosByIdTipoPreso(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateRegistroPresos(RegistroPresosDto registroPresosDto) {
        log.info("PUT /registroPresos");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroPresosService.updateRegistroPresos(registroPresosDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteRegistroPresos(Integer id) {
        log.info("DELETE /registroPresos/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroPresosService.deleteRegistroPresos(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}