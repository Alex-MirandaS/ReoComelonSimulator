package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewCajaIngredientesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.CajaIngredientesApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.CajaIngredientesDto;
import modela1.reo_comelon_simulator.service.CajaIngredientesService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CajaIngredientesController implements CajaIngredientesApi {
    private final CajaIngredientesService cajaIngredientesService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createCajaIngredientes(NewCajaIngredientesDto newCajaIngredientesDto) {
        log.info("POST /cajaIngredientes");
        ResponseSuccessfullyDto responseSuccessfullyDto = cajaIngredientesService.createCajaIngredientes(newCajaIngredientesDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllCajaIngredientes() {
        log.info("GET /cajaIngredientes/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = cajaIngredientesService.getAllCajaIngredientes();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getCajaIngredientesById(Integer id) {
        log.info("GET /cajaIngredientes/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = cajaIngredientesService.getCajaIngredientesById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateCajaIngredientes(CajaIngredientesDto cajaIngredientesDto) {
        log.info("PUT /cajaIngredientes");
        ResponseSuccessfullyDto responseSuccessfullyDto = cajaIngredientesService.updateCajaIngredientes(cajaIngredientesDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteCajaIngredientes(Integer id) {
        log.info("DELETE /cajaIngredientes/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = cajaIngredientesService.deleteCajaIngredientes(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}