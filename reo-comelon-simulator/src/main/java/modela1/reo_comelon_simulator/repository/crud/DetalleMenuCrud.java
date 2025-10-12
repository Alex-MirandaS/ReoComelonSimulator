package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.DetalleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleMenuCrud extends JpaRepository<DetalleMenu, Integer> {
    @Query(value = "select * from detalle_menu where id_menu = ? ", nativeQuery = true)
    List<DetalleMenu> getDetalleMenuByIdMenu(Integer id_menu);
}
