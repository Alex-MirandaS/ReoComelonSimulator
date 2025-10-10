package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewRegistroPresosDto {

    private Integer id_tipo_preso;

    private Integer cantidad;

    private Boolean activo;

}
