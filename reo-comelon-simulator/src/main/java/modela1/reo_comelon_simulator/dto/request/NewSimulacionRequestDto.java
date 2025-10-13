package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class NewSimulacionRequestDto {

    private Integer idRegistroPresos;
    private Integer idBodega;
    private Boolean esPremium;
    private Integer dias;
    private LocalDate fechaInicio;

}
