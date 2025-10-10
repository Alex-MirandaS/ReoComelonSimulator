package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroPresosDto;
import modela1.reo_comelon_simulator.repository.entities.BitacoraRegistroPresos;
import modela1.reo_comelon_simulator.repository.crud.BitacoraRegistroPresosCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class BitacoraRegistroPresosService {

    private final BitacoraRegistroPresosCrud bitacora_registro_presosCrud;

    public ResponseSuccessfullyDto getAllBitacoraRegistroPresos() {
        List<BitacoraRegistroPresos> list = bitacora_registro_presosCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getBitacoraRegistroPresosById(Integer id) {
        Optional<BitacoraRegistroPresos> optional = bitacora_registro_presosCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteBitacoraRegistroPresos(Integer id) {
        Optional<BitacoraRegistroPresos> optional = bitacora_registro_presosCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            bitacora_registro_presosCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

}
