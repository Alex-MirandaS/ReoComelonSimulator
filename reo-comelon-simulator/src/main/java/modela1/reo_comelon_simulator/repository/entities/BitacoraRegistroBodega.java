package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bitacora_registro_bodega")
public class BitacoraRegistroBodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bodega", referencedColumnName = "id")
    private RegistroBodega registroBodega;

    private Integer capacidadLibre;

    private Double capacidadUtilizada;

    private LocalDate fecha;
}
