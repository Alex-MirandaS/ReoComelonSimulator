package modela1.reo_comelon_simulator.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class NewRegistroComprasDto {

    private Integer id_simulacion;

    private Integer id_caja_ingredientes;

    private Double cantidad;

    private LocalDate fechaCompra;

    private LocalDate fechaVencimiento;
}
