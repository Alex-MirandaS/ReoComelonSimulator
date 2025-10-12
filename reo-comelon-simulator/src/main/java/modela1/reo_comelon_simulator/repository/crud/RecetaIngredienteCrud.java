package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaIngredienteCrud extends JpaRepository<RecetaIngrediente, Integer> {

    @Query(value = "select * from receta_ingrediente where id_receta = ? ", nativeQuery = true)
    List<RecetaIngrediente> getRecetaIngredByIdReceta(Integer id_receta);
}
