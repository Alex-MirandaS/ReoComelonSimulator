package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCrud extends JpaRepository<Menu, Integer> {
    @Query(value = "select * from menu where id_tipo_preso = ? ", nativeQuery = true)
    List<Menu> getMenuByIdTipoPreso(Integer id_tipo_preso);

    @Query(value = "    SELECT\n" +
            "    m.id,\n" +
            "    m.id_tipo_preso,\n" +
            "    m.fecha\n" +
            "    FROM simulacion_menus sm\n" +
            "    INNER JOIN registro_simulacion rs ON sm.id_simulacion = rs.id\n" +
            "    INNER JOIN menu m ON sm.id_menu = m.id\n" +
            "    WHERE rs.id = ?1;", nativeQuery = true)
    List<Menu> getMenuByIdSimulacion(Integer is_simulacion);
}
