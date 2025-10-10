package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "simulacion_menus")
public class SimulacionMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_simulacion", referencedColumnName = "id")
    private RegistroSimulacion registroSimulacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", referencedColumnName = "id")
    private Menu menu;
}
