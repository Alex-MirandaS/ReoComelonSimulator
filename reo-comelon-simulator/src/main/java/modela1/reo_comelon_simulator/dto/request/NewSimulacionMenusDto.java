package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewSimulacionMenusDto {

    private Integer id_simulacion;

    private Integer id_menu;

}
