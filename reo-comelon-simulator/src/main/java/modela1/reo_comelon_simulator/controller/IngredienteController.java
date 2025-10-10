package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewIngredienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.IngredienteApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.IngredienteDto;
import modela1.reo_comelon_simulator.service.IngredienteService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class IngredienteController implements IngredienteApi {
    private final IngredienteService ingredienteService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createIngrediente(NewIngredienteDto newIngredienteDto) {
        log.info("POST /ingrediente");
        ResponseSuccessfullyDto responseSuccessfullyDto = ingredienteService.createIngrediente(newIngredienteDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllIngrediente() {
        log.info("GET /ingrediente/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = ingredienteService.getAllIngrediente();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getIngredienteById(Integer id) {
        log.info("GET /ingrediente/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = ingredienteService.getIngredienteById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateIngrediente(IngredienteDto ingredienteDto) {
        log.info("PUT /ingrediente");
        ResponseSuccessfullyDto responseSuccessfullyDto = ingredienteService.updateIngrediente(ingredienteDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteIngrediente(Integer id) {
        log.info("DELETE /ingrediente/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = ingredienteService.deleteIngrediente(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}