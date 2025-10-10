package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistroBodegaDto {

    private Integer id;

    private String bodega;

    private Double capacidad;

}
