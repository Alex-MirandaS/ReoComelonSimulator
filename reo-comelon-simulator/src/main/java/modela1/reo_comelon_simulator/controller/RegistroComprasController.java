package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.controller.api.RegistroComprasApi;
import modela1.reo_comelon_simulator.dto.request.NewRegistroComprasDto;
import modela1.reo_comelon_simulator.dto.response.RegistroComprasDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.service.RegistroComprasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistroComprasController implements RegistroComprasApi {

    private final RegistroComprasService service;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createRegistroCompras(NewRegistroComprasDto dto) {
        log.info("POST /registroCompras");
        ResponseSuccessfullyDto response = service.createRegistroCompras(dto);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllRegistroCompras() {
        log.info("GET /registroCompras/all");
        ResponseSuccessfullyDto response = service.getAllRegistroCompras();
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getRegistroComprasById(Integer id) {
        log.info("GET /registroCompras/{}", id);
        ResponseSuccessfullyDto response = service.getRegistroComprasById(id);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getByIdSimulacion(Integer id) {
        log.info("GET /registroCompras/simulacion/{}", id);
        ResponseSuccessfullyDto response = service.getByIdSimulacion(id);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getByIdCajaIngredientes(Integer id) {
        log.info("GET /registroCompras/cajaIngredientes/{}", id);
        ResponseSuccessfullyDto response = service.getByIdCajaIngredientes(id);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateRegistroCompras(RegistroComprasDto dto) {
        log.info("PUT /registroCompras");
        ResponseSuccessfullyDto response = service.updateRegistroCompras(dto);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteRegistroCompras(Integer id) {
        log.info("DELETE /registroCompras/{}", id);
        ResponseSuccessfullyDto response = service.deleteRegistroCompras(id);
        return new ResponseEntity<>(response, response.getCode());
    }
}
