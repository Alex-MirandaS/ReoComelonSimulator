package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.controller.api.BitacoraRegistroBodegaApi;
import modela1.reo_comelon_simulator.dto.request.NewBitacoraRegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.service.BitacoraRegistroBodegaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BitacoraRegistroBodegaController implements BitacoraRegistroBodegaApi {

    private final BitacoraRegistroBodegaService service;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createBitacora(NewBitacoraRegistroBodegaDto dto) {
        log.info("POST /bitacoraBodega");
        ResponseSuccessfullyDto response = service.createBitacora(dto);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllBitacora() {
        log.info("GET /bitacoraBodega/all");
        ResponseSuccessfullyDto response = service.getAllBitacora();
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getBitacoraById(Integer id) {
        log.info("GET /bitacoraBodega/{}", id);
        ResponseSuccessfullyDto response = service.getBitacoraById(id);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getByIdBodega(Integer id) {
        log.info("GET /bitacoraBodega/bodega/{}", id);
        ResponseSuccessfullyDto response = service.getByIdBodega(id);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateBitacora(BitacoraRegistroBodegaDto dto) {
        log.info("PUT /bitacoraBodega");
        ResponseSuccessfullyDto response = service.updateBitacora(dto);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteBitacora(Integer id) {
        log.info("DELETE /bitacoraBodega/{}", id);
        ResponseSuccessfullyDto response = service.deleteBitacora(id);
        return new ResponseEntity<>(response, response.getCode());
    }
}
