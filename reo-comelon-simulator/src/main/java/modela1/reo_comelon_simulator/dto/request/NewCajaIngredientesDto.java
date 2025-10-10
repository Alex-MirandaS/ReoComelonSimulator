package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewCajaIngredientesDto {

    private Integer id_ocupacion_caja;

    private Integer cantidad;

    private Double precio;

    private Integer id_ingrediente;

}
