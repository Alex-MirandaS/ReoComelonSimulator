package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.BitacoraRegistroPresos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitacoraRegistroPresosCrud extends JpaRepository<BitacoraRegistroPresos, Integer> {
}
