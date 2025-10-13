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

    @Query(value = "SELECT " +
            "    r.id, " +
            "    r.nombre, " +
            "    r.id_Tipo_Receta, " +
            "    r.es_premium " +
            "FROM receta r " +
            "INNER JOIN receta_ingrediente ri ON r.id = ri.id_receta " +
            "INNER JOIN ingrediente i ON ri.id_ingrediente = i.id " +
            "INNER JOIN caja_ingredientes ci ON ci.id_ingrediente = i.id " +
            "WHERE r.id_Tipo_Receta = ?1 " +
            "  AND r.es_premium = ?2 " +
            "GROUP BY r.id, r.nombre, r.id_Tipo_Receta, r.es_premium " +
            "ORDER BY SUM(CEIL(ci.precio / ci.cantidad) * ri.cantidad) ASC",
            nativeQuery = true)
    List<Receta> getRecetaByIdTipoRecetaAndEsPremium(Integer id_Tipo_Receta, Boolean es_premium);

}
