package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewRegistroSimulacionDto;
import modela1.reo_comelon_simulator.dto.response.RegistroSimulacionDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.repository.entities.RegistroSimulacion;
import modela1.reo_comelon_simulator.repository.crud.RegistroSimulacionCrud;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistroSimulacionService {

    private final RegistroSimulacionCrud registro_simulacionCrud;
    private final RegistroPresosService registroPresosService;
    private final RegistroBodegaService registroBodegaService;

    public ResponseSuccessfullyDto getAllRegistroSimulacion() {
        List<RegistroSimulacion> list = registro_simulacionCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getRegistroSimulacionById(Integer id) {
        Optional<RegistroSimulacion> optional = registro_simulacionCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto deleteRegistroSimulacion(Integer id) {
        Optional<RegistroSimulacion> optional = registro_simulacionCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            registro_simulacionCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder().code(HttpStatus.ACCEPTED).message("Registro eliminado exitosamente").build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public RegistroSimulacion getRegistroSimulacionByIdRegistroSimulacion(Integer id) {
        Optional<RegistroSimulacion> optional = registro_simulacionCrud.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RegistroSimulacion not exists");
        }
        return optional.get();
    }

    public List<RegistroSimulacion> getAllRegistroSimulacionList() {
        List<RegistroSimulacion> list = registro_simulacionCrud.findAll();
        if(list.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"RegistroSimulacion not exists");
        }
        return list;
    }

    public ResponseSuccessfullyDto createRegistroSimulacion(NewRegistroSimulacionDto newRegistroSimulacionDto){
        RegistroSimulacion registroSimulacion = new RegistroSimulacion();
        registroSimulacion.setRegistroPresos(registroPresosService.getRegistroPresosByIdRegistroPresos(newRegistroSimulacionDto.getId_registro_presos()));
        registroSimulacion.setRegistroBodega(registroBodegaService.getRegistroBodegaByIdRegistroBodega(newRegistroSimulacionDto.getId_bodega()));
        registroSimulacion.setNombre(newRegistroSimulacionDto.getNombre());
        registroSimulacion.setDias(newRegistroSimulacionDto.getDias());
        LocalDate fechaInicio = LocalDate.now();
        if (newRegistroSimulacionDto.getFecha_inicio()!=null){
            fechaInicio = newRegistroSimulacionDto.getFecha_inicio();
        }
        registroSimulacion.setFecha_inicio(fechaInicio);
        registroSimulacion.setFecha_fin(fechaInicio.plusDays(newRegistroSimulacionDto.getDias()));
        registroSimulacion.setEs_premium(newRegistroSimulacionDto.getEs_premium());
        registroSimulacion.setPresupuesto(newRegistroSimulacionDto.getPresupuesto());
        registroSimulacion.setPerdida(newRegistroSimulacionDto.getPerdida());

        try{
            registro_simulacionCrud.save(registroSimulacion);
            return ResponseSuccessfullyDto.builder().code(HttpStatus.CREATED).message("RegistroSimulacion creado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al guardar el RegistroSimulacion");
        }
    }

    public ResponseSuccessfullyDto updateRegistroSimulacion(RegistroSimulacionDto registroSimulacionDto){

        Optional<RegistroSimulacion> optional = registro_simulacionCrud.findById(registroSimulacionDto.getId());

        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.NOT_FOUND,"El registro no ha sido encontrado");
        }
        RegistroSimulacion registroSimulacion = optional.get();
        registroSimulacion.setRegistroPresos(registroPresosService.getRegistroPresosByIdRegistroPresos(registroSimulacionDto.getId_registro_presos()));
        registroSimulacion.setRegistroBodega(registroBodegaService.getRegistroBodegaByIdRegistroBodega(registroSimulacionDto.getId_bodega()));
        registroSimulacion.setNombre(registroSimulacionDto.getNombre());
        registroSimulacion.setDias(registroSimulacionDto.getDias());
        registroSimulacion.setFecha_inicio(registroSimulacionDto.getFecha_inicio());
        registroSimulacion.setFecha_fin(registroSimulacionDto.getFecha_fin());
        registroSimulacion.setEs_premium(registroSimulacionDto.getEs_premium());
        registroSimulacion.setPresupuesto(registroSimulacionDto.getPresupuesto());
        registroSimulacion.setPerdida(registroSimulacionDto.getPerdida());

        try{
            registro_simulacionCrud.save(registroSimulacion);
            log.info("RegistroSimulacion actualizado...");
            return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).message("RegistroSimulacion actualizado exitosamente").build();
        }catch (Exception exception){
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Error al actualizar el RegistroSimulacion.");
        }
    }
}
