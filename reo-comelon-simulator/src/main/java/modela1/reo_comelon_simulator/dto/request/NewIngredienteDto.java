package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewIngredienteDto {

    private String ingrediente;

    private Integer vida_util_dias;

    private Integer cantidad;

}
