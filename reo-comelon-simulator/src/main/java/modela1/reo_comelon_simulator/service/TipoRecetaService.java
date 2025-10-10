package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.TipoRecetaDto;
import modela1.reo_comelon_simulator.repository.entities.TipoReceta;
import modela1.reo_comelon_simulator.repository.crud.TipoRecetaCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class TipoRecetaService {

    private final TipoRecetaCrud tipo_recetaCrud;

    public ResponseSuccessfullyDto getAllTipoReceta() {
        List<TipoReceta> list = tipo_recetaCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getTipoRecetaById(Integer id) {
        Optional<TipoReceta> optional = tipo_recetaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteTipoReceta(Integer id) {
        Optional<TipoReceta> optional = tipo_recetaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            tipo_recetaCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public TipoReceta getTipoRecetaByIdTipoReceta(Integer id){

        Optional<TipoReceta> optionalCard = tipo_recetaCrud.findById(id);

        if(optionalCard.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Card not exists");
        }

        return optionalCard.get();
    }

    public List<TipoReceta> getAllTipoRecetaList(){
        List<TipoReceta> cardList = tipo_recetaCrud.findAll();

        if(cardList.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Card's not exists");
        }

        return cardList;
    }

}
