package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewCajaIngredientesDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.CajaIngredientesDto;
import modela1.reo_comelon_simulator.repository.entities.CajaIngredientes;
import modela1.reo_comelon_simulator.repository.crud.CajaIngredientesCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class CajaIngredientesService {

    private final CajaIngredientesCrud caja_ingredientesCrud;
    private final OcupacionCajaService ocupacionCajaService;
    private final IngredienteService ingredienteService;

    public ResponseSuccessfullyDto getAllCajaIngredientes() {
        List<CajaIngredientes> list = caja_ingredientesCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getCajaIngredByIdIngrediente(Integer id) {
        List<CajaIngredientes> list = caja_ingredientesCrud.getCajaIngredByIdIngrediente(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getCajaIngredByIdOcupacionCaja(Integer id) {
        List<CajaIngredientes> list = caja_ingredientesCrud.getCajaIngredByIdOcupacionCaja(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getCajaIngredientesById(Integer id) {
        Optional<CajaIngredientes> optional = caja_ingredientesCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteCajaIngredientes(Integer id) {
        Optional<CajaIngredientes> optional = caja_ingredientesCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            caja_ingredientesCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto createCajaIngredientes(NewCajaIngredientesDto newDto) {
        CajaIngredientes obj = new CajaIngredientes();
        obj.setOcupacionCaja(ocupacionCajaService.getOcupacionCajaByIdOcupacionCaja(newDto.getId_ocupacion_caja()));
        obj.setCantidad(newDto.getCantidad());
        obj.setPrecio(newDto.getPrecio());
        obj.setIngrediente(ingredienteService.getIngredienteByIdIngrediente(newDto.getId_ingrediente()));
        try{
            caja_ingredientesCrud.save(obj);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("CajaIngredientes creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el registro");
        }
    }
    public ResponseSuccessfullyDto updateCajaIngredientes(CajaIngredientesDto dto) {
        Optional<CajaIngredientes> optional = caja_ingredientesCrud.findById(dto.getId());
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        CajaIngredientes obj = optional.get();
        obj.setOcupacionCaja(ocupacionCajaService.getOcupacionCajaByIdOcupacionCaja(dto.getId_ocupacion_caja()));
        obj.setCantidad(dto.getCantidad());
        obj.setPrecio(dto.getPrecio());
        obj.setIngrediente(ingredienteService.getIngredienteByIdIngrediente(dto.getId_ingrediente()));
        try{
            caja_ingredientesCrud.save(obj);
            log.info("Registro actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("CajaIngredientes actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el registro.");
        }
    }
    public CajaIngredientes getCajaIngredientesByIdCajaIngredientes(Integer id) {
        Optional<CajaIngredientes> optional = caja_ingredientesCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"CajaIngredientes not exists");
        }
        return optional.get();
    }
    public List<CajaIngredientes> getAllCajaIngredientesListByIdIngrediente(Integer id) {
        List<CajaIngredientes> list = caja_ingredientesCrud.getCajaIngredByIdIngrediente(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return list;
    }
}


