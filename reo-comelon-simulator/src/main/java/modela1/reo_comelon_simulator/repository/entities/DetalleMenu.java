package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalle_menu")
public class DetalleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", referencedColumnName = "id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta_des", referencedColumnName = "id")
    private Receta receta_des;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta_lunch", referencedColumnName = "id")
    private Receta receta_lunch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta_cena", referencedColumnName = "id")
    private Receta receta_cena;
}
