package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewRecetaIngredienteDto {

    private Integer id_receta;

    private Integer id_ingrediente;

    private Integer cantidad;

}
