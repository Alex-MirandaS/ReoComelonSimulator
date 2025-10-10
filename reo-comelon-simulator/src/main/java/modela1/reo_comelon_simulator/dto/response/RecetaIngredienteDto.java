package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecetaIngredienteDto {

    private Integer id;

    private Integer id_receta;

    private Integer id_ingrediente;

    private Integer cantidad;

}
