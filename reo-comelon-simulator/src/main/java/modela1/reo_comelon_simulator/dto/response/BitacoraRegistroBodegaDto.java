package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BitacoraRegistroBodegaDto {

    private Integer id;

    private Integer id_bodega;

    private Integer capacidadLibre;

    private Double capacidadUtilizada;

    private LocalDate fecha;
}
