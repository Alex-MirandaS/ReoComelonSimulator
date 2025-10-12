package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.RegistroPresos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPresosCrud extends JpaRepository<RegistroPresos, Integer> {
    @Query(value = "select * from registro_presos where id_tipo_preso = ? ", nativeQuery = true)
    List<RegistroPresos> getRegistroPresosByIdTipoPreso(Integer id_tipo_preso);
}
