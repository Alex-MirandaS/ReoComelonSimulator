package modela1.reo_comelon_simulator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewMenuDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import modela1.reo_comelon_simulator.controller.api.MenuApi;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.MenuDto;
import modela1.reo_comelon_simulator.service.MenuService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MenuController implements MenuApi {
    private final MenuService menuService;

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> createMenu(NewMenuDto newMenuDto) {
        log.info("POST /menu");
        ResponseSuccessfullyDto responseSuccessfullyDto = menuService.createMenu(newMenuDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getAllMenu() {
        log.info("GET /menu/all");
        ResponseSuccessfullyDto responseSuccessfullyDto = menuService.getAllMenu();
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> getMenuById(Integer id) {
        log.info("GET /menu/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = menuService.getMenuById(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> updateMenu(MenuDto menuDto) {
        log.info("PUT /menu");
        ResponseSuccessfullyDto responseSuccessfullyDto = menuService.updateMenu(menuDto);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }

    @Override
    public ResponseEntity<ResponseSuccessfullyDto> deleteMenu(Integer id) {
        log.info("DELETE /menu/{id}");
        ResponseSuccessfullyDto responseSuccessfullyDto = menuService.deleteMenu(id);
        return new ResponseEntity<>(responseSuccessfullyDto, responseSuccessfullyDto.getCode());
    }
}