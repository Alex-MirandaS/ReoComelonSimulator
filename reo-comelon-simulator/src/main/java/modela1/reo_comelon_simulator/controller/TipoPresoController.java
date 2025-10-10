package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.TipoPresoApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.TipoPresoDto;
import modela1.reo_comelon_simulator.service.TipoPresoService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TipoPresoController implements TipoPresoApi {
    private final TipoPresoService tipoPresoService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllTipoPreso() {
        log.info("GET /tipoPreso/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = tipoPresoService.getAllTipoPreso();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getTipoPresoById(Integer id) {
        log.info("GET /tipoPreso/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = tipoPresoService.getTipoPresoById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteTipoPreso(Integer id) {
        log.info("DELETE /tipoPreso/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = tipoPresoService.deleteTipoPreso(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}