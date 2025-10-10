package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewDetalleMenuDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.DetalleMenuApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.DetalleMenuDto;
import modela1.reo_comelon_simulator.service.DetalleMenuService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DetalleMenuController implements DetalleMenuApi {
    private final DetalleMenuService detalleMenuService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createDetalleMenu(NewDetalleMenuDto newDetalleMenuDto) {
        log.info("POST /detalleMenu");
        ResponseSuccessfullyDto responseSuccessfullyDto = detalleMenuService.createDetalleMenu(newDetalleMenuDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllDetalleMenu() {
        log.info("GET /detalleMenu/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = detalleMenuService.getAllDetalleMenu();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getDetalleMenuById(Integer id) {
        log.info("GET /detalleMenu/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = detalleMenuService.getDetalleMenuById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateDetalleMenu(DetalleMenuDto detalleMenuDto) {
        log.info("PUT /detalleMenu");
        ResponseSuccessfullyDto responseSuccessfullyDto = detalleMenuService.updateDetalleMenu(detalleMenuDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteDetalleMenu(Integer id) {
        log.info("DELETE /detalleMenu/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = detalleMenuService.deleteDetalleMenu(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}