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
}
