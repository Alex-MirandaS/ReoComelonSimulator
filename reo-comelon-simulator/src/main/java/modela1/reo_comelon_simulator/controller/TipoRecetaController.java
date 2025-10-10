package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.TipoRecetaApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.TipoRecetaDto;
import modela1.reo_comelon_simulator.service.TipoRecetaService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TipoRecetaController implements TipoRecetaApi {
    private final TipoRecetaService tipoRecetaService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllTipoReceta() {
        log.info("GET /tipoReceta/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = tipoRecetaService.getAllTipoReceta();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getTipoRecetaById(Integer id) {
        log.info("GET /tipoReceta/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = tipoRecetaService.getTipoRecetaById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteTipoReceta(Integer id) {
        log.info("DELETE /tipoReceta/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = tipoRecetaService.deleteTipoReceta(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}