package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "registro_simulacion")
public class RegistroSimulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer dias;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Boolean es_premium;
    private Double presupuesto;
    private Double perdida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_registro_presos", referencedColumnName = "id")
    private RegistroPresos registroPresos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bodega", referencedColumnName = "id")
    private RegistroBodega registroBodega;
}
