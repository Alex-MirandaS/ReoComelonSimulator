package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "registro_presos")
public class RegistroPresos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_preso", referencedColumnName = "id")

    private TipoPreso tipoPreso;
    private Integer cantidad;
    private Boolean activo;
}
