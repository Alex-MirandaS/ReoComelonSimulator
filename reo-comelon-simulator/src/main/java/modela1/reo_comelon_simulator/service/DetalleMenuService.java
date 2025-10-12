package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewDetalleMenuDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.entities.Menu;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.DetalleMenuDto;
import modela1.reo_comelon_simulator.repository.entities.DetalleMenu;
import modela1.reo_comelon_simulator.repository.crud.DetalleMenuCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class DetalleMenuService {

    private final DetalleMenuCrud detalle_menuCrud;
    private final MenuService menuService;
    private final RecetaService recetaService;


    public ResponseSuccessfullyDto createDetalleMenu(NewDetalleMenuDto newDto) {
        DetalleMenu obj = new DetalleMenu();
        obj.setMenu(menuService.getMenuByIdMenu(newDto.getId_menu()));
        obj.setReceta_des(recetaService.getRecetaByIdReceta(newDto.getId_receta_des()));
        obj.setReceta_lunch(recetaService.getRecetaByIdReceta(newDto.getId_receta_lunch()));
        obj.setReceta_cena(recetaService.getRecetaByIdReceta(newDto.getId_receta_cena()));
        try{
            detalle_menuCrud.save(obj);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("DetalleMenu creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el registro");
        }
    }
    public ResponseSuccessfullyDto updateDetalleMenu(DetalleMenuDto dto) {
        Optional<DetalleMenu> optional = detalle_menuCrud.findById(dto.getId());
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        DetalleMenu obj = optional.get();
        obj.setMenu(menuService.getMenuByIdMenu(dto.getId_menu()));
        obj.setReceta_des(recetaService.getRecetaByIdReceta(dto.getId_receta_des()));
        obj.setReceta_lunch(recetaService.getRecetaByIdReceta(dto.getId_receta_lunch()));
        obj.setReceta_cena(recetaService.getRecetaByIdReceta(dto.getId_receta_cena()));
        try{
            detalle_menuCrud.save(obj);
            log.info("Registro actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("DetalleMenu actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el registro.");
        }
    }

    public DetalleMenu getDetalleMenuByIdDetalleMenu(Integer id) {
        Optional<DetalleMenu> optional = detalle_menuCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"DetalleMenu not exists");
        }
        return optional.get();
    }

    public List<DetalleMenu> getAllDetalleMenuList() {
        List<DetalleMenu> list = detalle_menuCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"DetalleMenu not exists");
        }
        return list;
    }

    public ResponseSuccessfullyDto getAllDetalleMenu() {
        List<DetalleMenu> list = detalle_menuCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getDetalleMenuById(Integer id) {
        Optional<DetalleMenu> optional = detalle_menuCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteDetalleMenu(Integer id) {
        Optional<DetalleMenu> optional = detalle_menuCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            detalle_menuCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto getDetalleMenuByIdMenu(Integer id) {
        List<DetalleMenu> list = detalle_menuCrud.getDetalleMenuByIdMenu(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

}
