package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.BitacoraRegistroBodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraRegistroBodegaCrud extends JpaRepository<BitacoraRegistroBodega, Integer> {

    @Query(value = "SELECT * FROM bitacora_registro_bodega WHERE id_bodega = ?", nativeQuery = true)
    List<BitacoraRegistroBodega> getByIdBodega(Integer id_bodega);
}
