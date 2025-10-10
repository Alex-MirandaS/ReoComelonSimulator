package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "caja_ingredientes")
public class CajaIngredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocupacion_caja", referencedColumnName = "id")
    private OcupacionCaja ocupacionCaja;

    private Integer cantidad;
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingrediente", referencedColumnName = "id")
    private Ingrediente ingrediente;
}
