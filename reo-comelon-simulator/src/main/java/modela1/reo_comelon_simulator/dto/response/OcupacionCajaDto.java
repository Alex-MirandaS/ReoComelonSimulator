package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OcupacionCajaDto {

    private Integer id;

    private Integer ocupacion;

    private Boolean activo;

}
