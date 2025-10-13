package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaCrud extends JpaRepository<Receta, Integer> {
    @Query(value = "select * from receta where id_Tipo_Receta = ? ", nativeQuery = true)
    List<Receta> getRecetaByIdTipoReceta(Integer id_Tipo_Receta);

    @Query(value = "select * from receta where es_premium = ? ", nativeQuery = true)
    List<Receta> getRecetaByIdesPremiun(Boolean es_premium);

    @Query(value = "select * from receta where es_premium = ? and id_Tipo_Receta = ? ", nativeQuery = true)
    List<Receta> getRecetaByIdesPremiunIdTipoReceta(Boolean es_premium, Integer id_Tipo_Receta);
}
