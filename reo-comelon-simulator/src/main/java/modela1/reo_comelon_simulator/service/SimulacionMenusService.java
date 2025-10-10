package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewSimulacionMenusDto;
import modela1.reo_comelon_simulator.dto.response.SimulacionMenusDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.repository.entities.SimulacionMenus;
import modela1.reo_comelon_simulator.repository.crud.SimulacionMenusCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class SimulacionMenusService {

    private final SimulacionMenusCrud simulacion_menusCrud;
    private final RegistroSimulacionService simulacionService;
    private final MenuService menuService;

    public ResponseSuccessfullyDto getAllSimulacionMenus() {
        List<SimulacionMenus> list = simulacion_menusCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getSimulacionMenusById(Integer id) {
        Optional<SimulacionMenus> optional = simulacion_menusCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteSimulacionMenus(Integer id) {
        Optional<SimulacionMenus> optional = simulacion_menusCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            simulacion_menusCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto createSimulacionMenus(NewSimulacionMenusDto newDto) {
        SimulacionMenus obj = new SimulacionMenus();
        obj.setRegistroSimulacion(simulacionService.getRegistroSimulacionByIdRegistroSimulacion(newDto.getId_simulacion()));
        obj.setMenu(menuService.getMenuByIdMenu(newDto.getId_menu()));
        try{
            simulacion_menusCrud.save(obj);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("SimulacionMenus creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el registro");
        }
    }
    public ResponseSuccessfullyDto updateSimulacionMenus(SimulacionMenusDto dto) {
        Optional<SimulacionMenus> optional = simulacion_menusCrud.findById(dto.getId());
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        SimulacionMenus obj = optional.get();
        obj.setRegistroSimulacion(simulacionService.getRegistroSimulacionByIdRegistroSimulacion(dto.getId_simulacion()));
        obj.setMenu(menuService.getMenuByIdMenu(dto.getId_menu()));
        try{
            simulacion_menusCrud.save(obj);
            log.info("Registro actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("SimulacionMenus actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el registro.");
        }
    }
    public SimulacionMenus getSimulacionMenusByIdSimulacionMenus(Integer id) {
        Optional<SimulacionMenus> optional = simulacion_menusCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"SimulacionMenus not exists");
        }
        return optional.get();
    }
    public List<SimulacionMenus> getAllSimulacionMenusList() {
        List<SimulacionMenus> list = simulacion_menusCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"SimulacionMenus not exists");
        }
        return list;
    }

}
