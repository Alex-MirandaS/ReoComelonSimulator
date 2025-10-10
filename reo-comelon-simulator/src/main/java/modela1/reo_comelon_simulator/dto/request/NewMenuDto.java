package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class NewMenuDto {

    private Integer id_tipo_preso;

    private LocalDate fecha;

}
