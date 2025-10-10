package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRegistroBodegaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.RegistroBodegaApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.RegistroBodegaDto;
import modela1.reo_comelon_simulator.service.RegistroBodegaService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistroBodegaController implements RegistroBodegaApi {
    private final RegistroBodegaService registroBodegaService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createRegistroBodega(NewRegistroBodegaDto newRegistroBodegaDto) {
        log.info("POST /registroBodega");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroBodegaService.createRegistroBodega(newRegistroBodegaDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllRegistroBodega() {
        log.info("GET /registroBodega/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroBodegaService.getAllRegistroBodega();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getRegistroBodegaById(Integer id) {
        log.info("GET /registroBodega/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroBodegaService.getRegistroBodegaById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateRegistroBodega(RegistroBodegaDto registroBodegaDto) {
        log.info("PUT /registroBodega");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroBodegaService.updateRegistroBodega(registroBodegaDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteRegistroBodega(Integer id) {
        log.info("DELETE /registroBodega/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = registroBodegaService.deleteRegistroBodega(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}