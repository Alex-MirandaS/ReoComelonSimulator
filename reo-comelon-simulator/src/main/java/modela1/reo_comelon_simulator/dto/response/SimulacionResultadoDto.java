package modela1.reo_comelon_simulator.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import modela1.reo_comelon_simulator.repository.entities.Ingrediente;
import modela1.reo_comelon_simulator.repository.entities.Menu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class SimulacionResultadoDto {

    private Integer idSimulacion;
    private Double costoTotal;
    private Double espacioUsado;
    private Double espacioDisponible;
    private Double perdida;
    private List<Menu> menusGenerados;
    private List<Ingrediente> ingredientesRequeridos;

}
