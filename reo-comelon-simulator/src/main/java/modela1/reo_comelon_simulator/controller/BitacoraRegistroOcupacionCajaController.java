package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.BitacoraRegistroOcupacionCajaApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroOcupacionCajaDto;
import modela1.reo_comelon_simulator.service.BitacoraRegistroOcupacionCajaService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BitacoraRegistroOcupacionCajaController implements BitacoraRegistroOcupacionCajaApi {
    private final BitacoraRegistroOcupacionCajaService bitacoraRegistroOcupacionCajaService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllBitacoraRegistroOcupacionCaja() {
        log.info("GET /bitacoraRegistroOcupacionCaja/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroOcupacionCajaService.getAllBitacoraRegistroOcupacionCaja();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getBitacoraRegistroOcupacionCajaById(Integer id) {
        log.info("GET /bitacoraRegistroOcupacionCaja/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroOcupacionCajaService.getBitacoraRegistroOcupacionCajaById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getBitacoraRegistroOcupacionCajaByIdOcupacionCaja(Integer id) {
        log.info("GET /bitacoraRegistroOcupacionCaja/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroOcupacionCajaService.getBitacoraRegistroOcupacionCajaByIdOcupacionCaja(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteBitacoraRegistroOcupacionCaja(Integer id) {
        log.info("DELETE /bitacoraRegistroOcupacionCaja/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = bitacoraRegistroOcupacionCajaService.deleteBitacoraRegistroOcupacionCaja(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}