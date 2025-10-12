package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.BitacoraRegistroOcupacionCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraRegistroOcupacionCajaCrud extends JpaRepository<BitacoraRegistroOcupacionCaja, Integer> {
    @Query(value = "select * from bitacora_registro_ocupacion_caja where id_ocupacion_caja = ? ", nativeQuery = true)
    List<BitacoraRegistroOcupacionCaja> getBitacoraRegistroOcupacionCajaByIdOcupacionCaja(Integer id_registro_presos);
}
