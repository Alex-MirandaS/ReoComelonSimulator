package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRegistroBodegaDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.RegistroBodegaDto;
import modela1.reo_comelon_simulator.repository.entities.RegistroBodega;
import modela1.reo_comelon_simulator.repository.crud.RegistroBodegaCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistroBodegaService {

    private final RegistroBodegaCrud registro_bodegaCrud;

    public ResponseSuccessfullyDto getAllRegistroBodega() {
        List<RegistroBodega> list = registro_bodegaCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getRegistroBodegaById(Integer id) {
        Optional<RegistroBodega> optional = registro_bodegaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteRegistroBodega(Integer id) {
        Optional<RegistroBodega> optional = registro_bodegaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            registro_bodegaCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public RegistroBodega getRegistroBodegaByIdRegistroBodega(Integer id) {
        Optional<RegistroBodega> optional = registro_bodegaCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RegistroBodega not exists");
        }
        return optional.get();
    }

    public List<RegistroBodega> getAllRegistroBodegaList() {
        List<RegistroBodega> list = registro_bodegaCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RegistroBodega not exists");
        }
        return list;
    }

    public ResponseSuccessfullyDto createRegistroBodega(NewRegistroBodegaDto bodegaDto){
        RegistroBodega registroBodega = new RegistroBodega();
        registroBodega.setBodega(bodegaDto.getBodega());
        registroBodega.setCapacidad(bodegaDto.getCapacidad());

        try{
            registro_bodegaCrud.save(registroBodega);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("Registro creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el Registro");
        }
    }

    public ResponseSuccessfullyDto updateRegistroBodega(RegistroBodegaDto registroBodegaDto){

        Optional<RegistroBodega> optional = registro_bodegaCrud.findById(registroBodegaDto.getId());

        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        RegistroBodega registroBodega = new RegistroBodega();
        registroBodega.setBodega(registroBodegaDto.getBodega());
        registroBodega.setCapacidad(registroBodegaDto.getCapacidad());

        try{
            registro_bodegaCrud.save(registroBodega);
            log.info("Registro actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("Registro actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el Registro.");
        }
    }

}
