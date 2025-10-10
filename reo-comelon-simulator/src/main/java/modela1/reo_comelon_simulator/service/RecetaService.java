package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRecetaDto;
import modela1.reo_comelon_simulator.dto.response.MenuDto;
import modela1.reo_comelon_simulator.dto.response.RecetaDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.entities.Menu;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.repository.entities.Receta;
import modela1.reo_comelon_simulator.repository.crud.RecetaCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecetaService {

    private final RecetaCrud recetaCrud;
    private final TipoRecetaService tipoRecetaService;

    public ResponseSuccessfullyDto getAllReceta() {
        List<Receta> list = recetaCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getRecetaById(Integer id) {
        Optional<Receta> optional = recetaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteReceta(Integer id) {
        Optional<Receta> optional = recetaCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            recetaCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto createReceta(NewRecetaDto newRecetaDto){
        Receta receta = new Receta();
        receta.setTipoReceta(tipoRecetaService.getTipoRecetaByIdTipoReceta(newRecetaDto.getId_Tipo_Receta()));
        receta.setEs_premium(newRecetaDto.getEs_premium());

        try{
            recetaCrud.save(receta);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("Receta creada exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar la Receta");
        }
    }

    public ResponseSuccessfullyDto updateReceta(RecetaDto recetaDto){

        Optional<Receta> optional = recetaCrud.findById(recetaDto.getId());

        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        Receta receta = new Receta();
        receta.setTipoReceta(tipoRecetaService.getTipoRecetaByIdTipoReceta(recetaDto.getId_Tipo_Receta()));
        receta.setEs_premium(recetaDto.getEs_premium());

        try{
            recetaCrud.save(receta);
            log.info("Receta actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("Receta actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el Receta.");
        }
    }

    public Receta getRecetaByIdReceta(Integer id) {
        Optional<Receta> optional = recetaCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Receta not exists");
        }
        return optional.get();
    }

    public List<Receta> getAllRecetaList() {
        List<Receta> list = recetaCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Receta not exists");
        }
        return list;
    }

}
