package modela1.reo_comelon_simulator.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "receta_ingrediente")
public class RecetaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_receta", referencedColumnName = "id")
    private Receta receta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ingrediente", referencedColumnName = "id")
    private Ingrediente ingrediente;

    private Integer cantidad;
}
