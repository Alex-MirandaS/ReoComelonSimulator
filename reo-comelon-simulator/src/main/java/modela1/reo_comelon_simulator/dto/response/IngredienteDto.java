package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IngredienteDto {

    private Integer id;

    private String ingrediente;

    private Integer vida_util_dias;

    private Integer cantidad;

}
