package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRecetaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.RecetaApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.RecetaDto;
import modela1.reo_comelon_simulator.service.RecetaService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RecetaController implements RecetaApi {
    private final RecetaService recetaService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createReceta(NewRecetaDto newRecetaDto) {
        log.info("POST /receta");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaService.createReceta(newRecetaDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllReceta() {
        log.info("GET /receta/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaService.getAllReceta();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getRecetaById(Integer id) {
        log.info("GET /receta/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaService.getRecetaById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateReceta(RecetaDto recetaDto) {
        log.info("PUT /receta");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaService.updateReceta(recetaDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteReceta(Integer id) {
        log.info("DELETE /receta/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaService.deleteReceta(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}