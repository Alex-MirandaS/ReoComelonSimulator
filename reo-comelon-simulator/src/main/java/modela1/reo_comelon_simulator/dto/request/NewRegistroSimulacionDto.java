package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class NewRegistroSimulacionDto {

    private Integer id_registro_presos;

    private Integer id_bodega;

    private String nombre;

    private Integer dias;

    private LocalDate fecha_inicio;

    private LocalDate fecha_fin;

    private Boolean es_premium;

    private Double presupuesto;

    private Double perdida;

}
