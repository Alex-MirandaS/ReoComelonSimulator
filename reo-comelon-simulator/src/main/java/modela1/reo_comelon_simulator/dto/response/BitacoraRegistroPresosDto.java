package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class BitacoraRegistroPresosDto {

    private Integer id;

    private Integer id_registro_presos;

    private LocalDate fecha;

    private LocalTime hora;

    private Boolean es_insert;

}
