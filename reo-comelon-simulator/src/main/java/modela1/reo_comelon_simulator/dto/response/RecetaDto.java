package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecetaDto {

    private Integer id;

    private Integer id_Tipo_Receta;

    private Boolean es_premium;

}
