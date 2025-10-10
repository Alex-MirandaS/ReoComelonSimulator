package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "bitacora_registro_ocupacion_caja")
public class BitacoraRegistroOcupacionCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocupacion_caja", referencedColumnName = "id")
    private OcupacionCaja ocupacionCaja;

    private LocalDate fecha;
    private LocalTime hora;
    private Boolean es_insert;
}
