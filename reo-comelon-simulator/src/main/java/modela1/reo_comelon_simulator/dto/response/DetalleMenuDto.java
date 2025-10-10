package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetalleMenuDto {

    private Integer id;

    private Integer id_menu;

    private Integer id_receta_des;

    private Integer id_receta_lunch;

    private Integer id_receta_cena;

}
