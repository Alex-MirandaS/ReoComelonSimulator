package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewOcupacionCajaDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.entities.Receta;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.OcupacionCajaDto;
import modela1.reo_comelon_simulator.repository.entities.OcupacionCaja;
import modela1.reo_comelon_simulator.repository.crud.OcupacionCajaCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class OcupacionCajaService {

    private final OcupacionCajaCrud ocupacion_cajaCrud;

    public ResponseSuccessfullyDto getAllOcupacionCaja() {
        List<OcupacionCaja> list = ocupacion_cajaCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getOcupacionCajaById(Integer id) {
        Optional<OcupacionCaja> optional = ocupacion_cajaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteOcupacionCaja(Integer id) {
        Optional<OcupacionCaja> optional = ocupacion_cajaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            ocupacion_cajaCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public OcupacionCaja getOcupacionCajaByIdOcupacionCaja(Integer id) {
        Optional<OcupacionCaja> optional = ocupacion_cajaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "OcupacionCaja not exists");
        }
        return optional.get();
    }

    public List<OcupacionCaja> getAllOcupacionCajaList() {
        List<OcupacionCaja> list = ocupacion_cajaCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "OcupacionCaja not exists");
        }
        return list;
    }

    public ResponseSuccessfullyDto createOcupacionCaja(NewOcupacionCajaDto newOcupacionCajaDto){
        OcupacionCaja ocupacionCaja = new OcupacionCaja();
        ocupacionCaja.setOcupacion(newOcupacionCajaDto.getOcupacion());
        ocupacionCaja.setActivo(newOcupacionCajaDto.getActivo());

        try{
            ocupacion_cajaCrud.save(ocupacionCaja);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("Ocupacion Caja creada exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar la Ocupacion Caja");
        }
    }

    public ResponseSuccessfullyDto updateOcupacionCaja(OcupacionCajaDto ocupacionCajaDto){

        Optional<OcupacionCaja> optional = ocupacion_cajaCrud.findById(ocupacionCajaDto.getId());

        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        OcupacionCaja ocupacionCaja = new OcupacionCaja();
        ocupacionCaja.setOcupacion(ocupacionCajaDto.getOcupacion());
        ocupacionCaja.setActivo(ocupacionCajaDto.getActivo());

        try{
            ocupacion_cajaCrud.save(ocupacionCaja);
            log.info("Menú actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("Ocupacion Caja actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el Ocupacion Caja.");
        }
    }

}
