package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.SimulacionMenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulacionMenusCrud extends JpaRepository<SimulacionMenus, Integer> {
    @Query(value = "select * from simulacion_menus where id_simulacion = ? ", nativeQuery = true)
    List<SimulacionMenus> getSimulacionMenuByIdSimulacion(Integer id_simulacion);
}
