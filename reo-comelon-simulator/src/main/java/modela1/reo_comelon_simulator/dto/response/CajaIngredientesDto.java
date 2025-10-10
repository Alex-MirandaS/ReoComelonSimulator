package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CajaIngredientesDto {

    private Integer id;

    private Integer id_ocupacion_caja;

    private Integer cantidad;

    private Double precio;

    private Integer id_ingrediente;

}
