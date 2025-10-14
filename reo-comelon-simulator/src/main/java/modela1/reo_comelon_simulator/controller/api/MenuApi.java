package modela1.reo_comelon_simulator.controller.api;

import modela1.reo_comelon_simulator.dto.request.NewMenuDto;
import modela1.reo_comelon_simulator.dto.response.MenuDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/menu")
public interface MenuApi {
    @PostMapping
    ResponseEntity<ResponseSuccessfullyDto> createMenu(@RequestBody NewMenuDto newMenuDto);

    @GetMapping("/all")
    ResponseEntity<ResponseSuccessfullyDto> getAllMenu();

    @GetMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getMenuById(@PathVariable Integer id);

    @GetMapping("/tipoPreso/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getMenuByIdTipoPreso(@PathVariable Integer id);

    @GetMapping("/simulacion/{id}")
    ResponseEntity<ResponseSuccessfullyDto> getMenuByIdSimulacion(@PathVariable Integer id);

    @PutMapping
    ResponseEntity<ResponseSuccessfullyDto> updateMenu(@RequestBody MenuDto menuDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseSuccessfullyDto> deleteMenu(@PathVariable Integer id);
}