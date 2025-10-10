package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRecetaIngredienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.RecetaIngredienteApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.RecetaIngredienteDto;
import modela1.reo_comelon_simulator.service.RecetaIngredienteService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RecetaIngredienteController implements RecetaIngredienteApi {
    private final RecetaIngredienteService recetaIngredienteService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createRecetaIngrediente(NewRecetaIngredienteDto newRecetaIngredienteDto) {
        log.info("POST /recetaIngrediente");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaIngredienteService.createRecetaIngrediente(newRecetaIngredienteDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllRecetaIngrediente() {
        log.info("GET /recetaIngrediente/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaIngredienteService.getAllRecetaIngrediente();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getRecetaIngredienteById(Integer id) {
        log.info("GET /recetaIngrediente/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaIngredienteService.getRecetaIngredienteById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateRecetaIngrediente(RecetaIngredienteDto recetaIngredienteDto) {
        log.info("PUT /recetaIngrediente");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaIngredienteService.updateRecetaIngrediente(recetaIngredienteDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteRecetaIngrediente(Integer id) {
        log.info("DELETE /recetaIngrediente/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = recetaIngredienteService.deleteRecetaIngrediente(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}