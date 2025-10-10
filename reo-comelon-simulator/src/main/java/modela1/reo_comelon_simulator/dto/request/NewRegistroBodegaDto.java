package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewRegistroBodegaDto {

    private String bodega;

    private Double capacidad;

}
