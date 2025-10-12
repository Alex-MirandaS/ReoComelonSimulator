package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "bitacora_registro_presos")
public class BitacoraRegistroPresos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_registro_presos", referencedColumnName = "id")
    private RegistroPresos registroPresos;

    private LocalDate fecha;
    private LocalTime hora;
    private Boolean es_insert;
}
