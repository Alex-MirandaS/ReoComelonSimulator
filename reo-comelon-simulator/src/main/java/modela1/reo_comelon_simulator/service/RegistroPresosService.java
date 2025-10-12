package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRegistroPresosDto;
import modela1.reo_comelon_simulator.dto.response.MenuDto;
import modela1.reo_comelon_simulator.dto.response.RegistroPresosDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.entities.Menu;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.repository.entities.RegistroPresos;
import modela1.reo_comelon_simulator.repository.crud.RegistroPresosCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistroPresosService {

    private final RegistroPresosCrud registro_presosCrud;
    private final TipoPresoService tipoPresoService;

    public ResponseSuccessfullyDto getAllRegistroPresos() {
        List<RegistroPresos> list = registro_presosCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getRegistroPresosByIdTipoPreso(Integer id) {
        List<RegistroPresos> list = registro_presosCrud.getRegistroPresosByIdTipoPreso(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getRegistroPresosById(Integer id) {
        Optional<RegistroPresos> optional = registro_presosCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteRegistroPresos(Integer id) {
        Optional<RegistroPresos> optional = registro_presosCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            registro_presosCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public RegistroPresos getRegistroPresosByIdRegistroPresos(Integer id) {
        Optional<RegistroPresos> optional = registro_presosCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RegistroPresos not exists");
        }
        return optional.get();
    }

    public List<RegistroPresos> getAllRegistroPresosList() {
        List<RegistroPresos> list = registro_presosCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RegistroPresos not exists");
        }
        return list;
    }

    public ResponseSuccessfullyDto createRegistroPresos(NewRegistroPresosDto newRegistroPresosDto){
        RegistroPresos registroPresos = new RegistroPresos();
        registroPresos.setTipoPreso(tipoPresoService.getTipoPresoByIdTipoPreso(newRegistroPresosDto.getId_tipo_preso()));
        registroPresos.setCantidad(newRegistroPresosDto.getCantidad());
        registroPresos.setActivo(newRegistroPresosDto.getActivo());

        try{
            registro_presosCrud.save(registroPresos);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("RegistroPresos creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el RegistroPresos");
        }
    }

    public ResponseSuccessfullyDto updateRegistroPresos(RegistroPresosDto registroPresosDto){

        Optional<RegistroPresos> optional = registro_presosCrud.findById(registroPresosDto.getId());

        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        RegistroPresos registroPresos = new RegistroPresos();
        registroPresos.setTipoPreso(tipoPresoService.getTipoPresoByIdTipoPreso(registroPresosDto.getId_tipo_preso()));
        registroPresos.setCantidad(registroPresosDto.getCantidad());
        registroPresos.setActivo(registroPresosDto.getActivo());

        try{
            registro_presosCrud.save(registroPresos);
            log.info("RegistroPresos actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("RegistroPresos actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el RegistroPresos.");
        }
    }

}
