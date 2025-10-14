package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRegistroComprasDto;
import modela1.reo_comelon_simulator.dto.response.RegistroComprasDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.crud.RegistroComprasCrud;
import modela1.reo_comelon_simulator.repository.entities.RegistroCompras;
import modela1.reo_comelon_simulator.service.CajaIngredientesService;
import modela1.reo_comelon_simulator.service.RegistroSimulacionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistroComprasService {

    private final RegistroComprasCrud registroComprasCrud;
    private final RegistroSimulacionService registroSimulacionService;
    private final CajaIngredientesService cajaIngredientesService;

    public ResponseSuccessfullyDto getAllRegistroCompras() {
        List<RegistroCompras> list = registroComprasCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getRegistroComprasById(Integer id) {
        Optional<RegistroCompras> optional = registroComprasCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto getByIdSimulacion(Integer id) {
        List<RegistroCompras> list = registroComprasCrud.getByIdSimulacion(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getByIdCajaIngredientes(Integer id) {
        List<RegistroCompras> list = registroComprasCrud.getByIdCajaIngredientes(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto deleteRegistroCompras(Integer id) {
        Optional<RegistroCompras> optional = registroComprasCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            registroComprasCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder()
                    .code(HttpStatus.ACCEPTED)
                    .message("Registro eliminado exitosamente")
                    .build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto createRegistroCompras(NewRegistroComprasDto dto) {
        RegistroCompras registro = new RegistroCompras();
        registro.setCantidad(dto.getCantidad());
        registro.setFechaCompra(dto.getFechaCompra());
        registro.setFechaVencimiento(dto.getFechaVencimiento());
        registro.setRegistroSimulacion(registroSimulacionService.getRegistroSimulacionByIdRegistroSimulacion(dto.getId_simulacion()));
        registro.setCajaIngredientes(cajaIngredientesService.getCajaIngredientesByIdCajaIngredientes(dto.getId_caja_ingredientes()));

        try {
            registroComprasCrud.save(registro);
            return ResponseSuccessfullyDto.builder()
                    .code(HttpStatus.CREATED)
                    .message("Registro de compra creado exitosamente")
                    .build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al guardar el registro de compra");
        }
    }

    public ResponseSuccessfullyDto updateRegistroCompras(RegistroComprasDto dto) {
        Optional<RegistroCompras> optional = registroComprasCrud.findById(dto.getId());
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }

        RegistroCompras registro = optional.get();
        registro.setCantidad(dto.getCantidad());
        registro.setFechaCompra(dto.getFechaCompra());
        registro.setFechaVencimiento(dto.getFechaVencimiento());
        registro.setRegistroSimulacion(registroSimulacionService.getRegistroSimulacionByIdRegistroSimulacion(dto.getId_simulacion()));
        registro.setCajaIngredientes(cajaIngredientesService.getCajaIngredientesByIdCajaIngredientes(dto.getId_caja_ingredientes()));

        try {
            registroComprasCrud.save(registro);
            log.info("Registro de compra actualizado...");
            return ResponseSuccessfullyDto.builder()
                    .code(HttpStatus.OK)
                    .message("Registro de compra actualizado exitosamente")
                    .build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al actualizar el registro de compra");
        }
    }
}
