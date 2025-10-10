package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SimulacionMenusDto {

    private Integer id;

    private Integer id_simulacion;

    private Integer id_menu;

}
