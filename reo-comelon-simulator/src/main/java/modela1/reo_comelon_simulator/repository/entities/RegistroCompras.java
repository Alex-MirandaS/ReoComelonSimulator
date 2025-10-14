package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "registro_compras")
public class RegistroCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_simulacion", referencedColumnName = "id")
    private RegistroSimulacion registroSimulacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_caja_ingredientes", referencedColumnName = "id")
    private CajaIngredientes cajaIngredientes;

    private Double cantidad;

    private LocalDate fechaCompra;

    private LocalDate fechaVencimiento;
}
