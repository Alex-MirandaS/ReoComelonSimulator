package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewMenuDto;
import modela1.reo_comelon_simulator.dto.response.MenuDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.crud.TipoPresoCrud;
import modela1.reo_comelon_simulator.repository.entities.SimulacionMenus;
import modela1.reo_comelon_simulator.repository.entities.TipoReceta;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.repository.entities.Menu;
import modela1.reo_comelon_simulator.repository.crud.MenuCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuCrud menuCrud;

    private final TipoPresoService tipoPresoService;

    public ResponseSuccessfullyDto getAllMenu() {
        List<Menu> list = menuCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getMenuById(Integer id) {
        Optional<Menu> optional = menuCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteMenu(Integer id) {
        Optional<Menu> optional = menuCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            menuCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto createMenu(NewMenuDto newMenuDto){
        Menu menu = new Menu();
        menu.setFecha(newMenuDto.getFecha());
        menu.setTipoPreso(tipoPresoService.getTipoPresoByIdTipoPreso(newMenuDto.getId_tipo_preso()));

        try{
            menuCrud.save(menu);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("Menú creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el Menú");
        }
    }

    public ResponseSuccessfullyDto updateMenu(MenuDto menuDto){

        Optional<Menu> optional = menuCrud.findById(menuDto.getId());

        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        Menu menu = optional.get();
        menu.setFecha(menuDto.getFecha());
        menu.setTipoPreso(tipoPresoService.getTipoPresoByIdTipoPreso(menuDto.getId_tipo_preso()));

        try{
            menuCrud.save(menu);
            log.info("Menú actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("Menú actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el Menú.");
        }
    }

    public Menu getMenuByIdMenu(Integer id){

        Optional<Menu> optional = menuCrud.findById(id);

        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Menu not exists");
        }

        return optional.get();
    }

    public List<Menu> getAllMenuList(){
        List<Menu> list = menuCrud.findAll();

        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"Menu not exists");
        }

        return list;
    }

}
