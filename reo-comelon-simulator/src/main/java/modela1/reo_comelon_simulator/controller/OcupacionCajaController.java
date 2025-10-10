package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewOcupacionCajaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.OcupacionCajaApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.OcupacionCajaDto;
import modela1.reo_comelon_simulator.service.OcupacionCajaService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OcupacionCajaController implements OcupacionCajaApi {
    private final OcupacionCajaService ocupacionCajaService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createOcupacionCaja(NewOcupacionCajaDto newOcupacionCajaDto) {
        log.info("POST /ocupacionCaja");
        ResponseSuccessfullyDto responseSuccessfullyDto = ocupacionCajaService.createOcupacionCaja(newOcupacionCajaDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllOcupacionCaja() {
        log.info("GET /ocupacionCaja/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = ocupacionCajaService.getAllOcupacionCaja();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getOcupacionCajaById(Integer id) {
        log.info("GET /ocupacionCaja/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = ocupacionCajaService.getOcupacionCajaById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateOcupacionCaja(OcupacionCajaDto ocupacionCajaDto) {
        log.info("PUT /ocupacionCaja");
        ResponseSuccessfullyDto responseSuccessfullyDto = ocupacionCajaService.updateOcupacionCaja(ocupacionCajaDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteOcupacionCaja(Integer id) {
        log.info("DELETE /ocupacionCaja/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = ocupacionCajaService.deleteOcupacionCaja(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}