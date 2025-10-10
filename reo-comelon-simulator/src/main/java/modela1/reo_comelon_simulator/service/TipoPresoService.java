package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.entities.TipoReceta;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.TipoPresoDto;
import modela1.reo_comelon_simulator.repository.entities.TipoPreso;
import modela1.reo_comelon_simulator.repository.crud.TipoPresoCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class TipoPresoService {

    private final TipoPresoCrud tipo_presoCrud;

    public ResponseSuccessfullyDto getAllTipoPreso() {
        List<TipoPreso> list = tipo_presoCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getTipoPresoById(Integer id) {
        Optional<TipoPreso> optional = tipo_presoCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteTipoPreso(Integer id) {
        Optional<TipoPreso> optional = tipo_presoCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            tipo_presoCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public TipoPreso getTipoPresoByIdTipoPreso(Integer id){

        Optional<TipoPreso> optionalCard = tipo_presoCrud.findById(id);

        if(optionalCard.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Card not exists");
        }

        return optionalCard.get();
    }

    public List<TipoPreso> getAllTipoPresoList(){
        List<TipoPreso> cardList = tipo_presoCrud.findAll();

        if(cardList.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Card's not exists");
        }

        return cardList;
    }

}
