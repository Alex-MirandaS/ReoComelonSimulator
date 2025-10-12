package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.BitacoraRegistroPresosApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroPresosDto;
import modela1.reo_comelon_simulator.service.BitacoraRegistroPresosService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BitacoraRegistroPresosController implements BitacoraRegistroPresosApi {
    private final BitacoraRegistroPresosService bitacoraRegistroPresosService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllBitacoraRegistroPresos() {
        log.info("GET /bitacoraRegistroPresos/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroPresosService.getAllBitacoraRegistroPresos();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getBitacoraRegistroPresosById(Integer id) {
        log.info("GET /bitacoraRegistroPresos/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroPresosService.getBitacoraRegistroPresosById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getBitacoraRegistroPresosByIdRegistroPresos(Integer id) {
        log.info("GET /bitacoraRegistroPresos/registroPresos/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroPresosService.getBitacoraRegistroPresosByIdRegistroPresos(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteBitacoraRegistroPresos(Integer id) {
        log.info("DELETE /bitacoraRegistroPresos/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroPresosService.deleteBitacoraRegistroPresos(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}