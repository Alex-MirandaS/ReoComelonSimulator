package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewBitacoraRegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.BitacoraRegistroBodegaDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.crud.BitacoraRegistroBodegaCrud;
import modela1.reo_comelon_simulator.repository.entities.BitacoraRegistroBodega;
import modela1.reo_comelon_simulator.service.RegistroBodegaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BitacoraRegistroBodegaService {

    private final BitacoraRegistroBodegaCrud bitacoraCrud;
    private final RegistroBodegaService registroBodegaService;

    public ResponseSuccessfullyDto getAllBitacora() {
        List<BitacoraRegistroBodega> list = bitacoraCrud.findAll();
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto getBitacoraById(Integer id) {
        Optional<BitacoraRegistroBodega> optional = bitacoraCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(optional.get()).build();
    }

    public ResponseSuccessfullyDto getByIdBodega(Integer id) {
        List<BitacoraRegistroBodega> list = bitacoraCrud.getByIdBodega(id);
        if (list.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontraron registros");
        }
        return ResponseSuccessfullyDto.builder().code(HttpStatus.OK).body(list).build();
    }

    public ResponseSuccessfullyDto deleteBitacora(Integer id) {
        Optional<BitacoraRegistroBodega> optional = bitacoraCrud.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }
        try {
            bitacoraCrud.delete(optional.get());
            return ResponseSuccessfullyDto.builder()
                    .code(HttpStatus.ACCEPTED)
                    .message("Registro eliminado exitosamente")
                    .build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al eliminar el registro");
        }
    }

    public ResponseSuccessfullyDto createBitacora(NewBitacoraRegistroBodegaDto dto) {
        BitacoraRegistroBodega registro = new BitacoraRegistroBodega();
        registro.setCapacidadLibre(dto.getCapacidadLibre());
        registro.setCapacidadUtilizada(dto.getCapacidadUtilizada());
        registro.setFecha(dto.getFecha());
        registro.setRegistroBodega(registroBodegaService.getRegistroBodegaByIdRegistroBodega(dto.getId_bodega()));

        try {
            bitacoraCrud.save(registro);
            return ResponseSuccessfullyDto.builder()
                    .code(HttpStatus.CREATED)
                    .message("Bitácora creada exitosamente")
                    .build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al guardar la bitácora");
        }
    }

    public ResponseSuccessfullyDto updateBitacora(BitacoraRegistroBodegaDto dto) {
        Optional<BitacoraRegistroBodega> optional = bitacoraCrud.findById(dto.getId());
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Registro no encontrado");
        }

        BitacoraRegistroBodega registro = optional.get();
        registro.setCapacidadLibre(dto.getCapacidadLibre());
        registro.setCapacidadUtilizada(dto.getCapacidadUtilizada());
        registro.setFecha(dto.getFecha());
        registro.setRegistroBodega(registroBodegaService.getRegistroBodegaByIdRegistroBodega(dto.getId_bodega()));

        try {
            bitacoraCrud.save(registro);
            log.info("Bitácora actualizada...");
            return ResponseSuccessfullyDto.builder()
                    .code(HttpStatus.OK)
                    .message("Bitácora actualizada exitosamente")
                    .build();
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error al actualizar la bitácora");
        }
    }
}
