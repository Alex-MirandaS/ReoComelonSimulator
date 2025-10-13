package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewIngredienteDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.entities.CajaIngredientes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.IngredienteDto;
import modela1.reo_comelon_simulator.repository.entities.Ingrediente;
import modela1.reo_comelon_simulator.repository.crud.IngredienteCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class IngredienteService {

    private final IngredienteCrud ingredienteCrud;

    public ResponseSuccessfullyDto getAllIngrediente() {
        List<Ingrediente> list = ingredienteCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getIngredienteById(Integer id) {
        Optional<Ingrediente> optional = ingredienteCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteIngrediente(Integer id) {
        Optional<Ingrediente> optional = ingredienteCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            ingredienteCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto createIngrediente(NewIngredienteDto newDto) {
        Ingrediente obj = new Ingrediente();
        obj.setIngrediente(newDto.getIngrediente());
        obj.setCantidad(0);
        obj.setVida_util_dias(newDto.getVida_util_dias());

        try{
            Ingrediente saved = ingredienteCrud.save(obj);

            // Retornar el ID dentro del body del DTO
            return ResponseSuccessfullyDto.builder()
                    .code(HttpStatus.CREATED)
                    .message("Ingrediente creado exitosamente")
                    .body(Map.of("id", saved.getId()))  // <-- aquí agregamos el ID
                    .build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el registro");
        }
    }
    public ResponseSuccessfullyDto updateIngrediente(IngredienteDto dto) {
        Optional<Ingrediente> optional = ingredienteCrud.findById(dto.getId());
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        Ingrediente obj = new Ingrediente();
        obj.setIngrediente(dto.getIngrediente());
        obj.setCantidad(dto.getCantidad());
        obj.setVida_util_dias(dto.getVida_util_dias());
        try{
            ingredienteCrud.save(obj);
            log.info("Registro actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("Ingredientes actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el registro.");
        }
    }

    public Ingrediente getIngredienteByIdIngrediente(Integer id) {
        Optional<Ingrediente> optional = ingredienteCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Ingrediente not exists");
        }
        return optional.get();
    }

    public List<Ingrediente> getAllIngredienteList() {
        List<Ingrediente> list = ingredienteCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Ingrediente not exists");
        }
        return list;
    }

}
