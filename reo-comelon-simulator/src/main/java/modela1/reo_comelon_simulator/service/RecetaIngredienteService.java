package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRecetaIngredienteDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.RecetaIngredienteDto;
import modela1.reo_comelon_simulator.repository.entities.RecetaIngrediente;
import modela1.reo_comelon_simulator.repository.crud.RecetaIngredienteCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecetaIngredienteService {

    private final RecetaIngredienteCrud receta_ingredienteCrud;

    public ResponseSuccessfullyDto getAllRecetaIngrediente() {
        List<RecetaIngrediente> list = receta_ingredienteCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getRecetaIngredienteById(Integer id) {
        Optional<RecetaIngrediente> optional = receta_ingredienteCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteRecetaIngrediente(Integer id) {
        Optional<RecetaIngrediente> optional = receta_ingredienteCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            receta_ingredienteCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    private final RecetaService recetaService;
    private final IngredienteService ingredienteService;


    public ResponseSuccessfullyDto createRecetaIngrediente(NewRecetaIngredienteDto newDto) {
        RecetaIngrediente obj = new RecetaIngrediente();
        obj.setReceta(recetaService.getRecetaByIdReceta(newDto.getId_receta()));
        obj.setIngrediente(ingredienteService.getIngredienteByIdIngrediente(newDto.getId_ingrediente()));
        obj.setCantidad(newDto.getCantidad());
        try{
            receta_ingredienteCrud.save(obj);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("RecetaIngrediente creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el registro");
        }
    }
    public ResponseSuccessfullyDto updateRecetaIngrediente(RecetaIngredienteDto dto) {
        Optional<RecetaIngrediente> optional = receta_ingredienteCrud.findById(dto.getId());
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        RecetaIngrediente obj = optional.get();
        obj.setReceta(recetaService.getRecetaByIdReceta(dto.getId_receta()));
        obj.setIngrediente(ingredienteService.getIngredienteByIdIngrediente(dto.getId_ingrediente()));
        obj.setCantidad(dto.getCantidad());
        try{
            receta_ingredienteCrud.save(obj);
            log.info("Registro actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("RecetaIngrediente actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el registro.");
        }
    }
    public RecetaIngrediente getRecetaIngredienteByIdRecetaIngrediente(Integer id) {
        Optional<RecetaIngrediente> optional = receta_ingredienteCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RecetaIngrediente not exists");
        }
        return optional.get();
    }
    public List<RecetaIngrediente> getAllRecetaIngredienteList() {
        List<RecetaIngrediente> list = receta_ingredienteCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RecetaIngrediente not exists");
        }
        return list;
    }

}
