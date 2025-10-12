package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.CajaIngredientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CajaIngredientesCrud extends JpaRepository<CajaIngredientes, Integer> {
    @Query(value = "select * from caja_ingredientes where id_ingrediente = ? ", nativeQuery = true)
    List<CajaIngredientes> getCajaIngredByIdIngrediente(Integer id_ingrediente);

    @Query(value = "select * from caja_ingredientes where id_ocupacion_caja = ? ", nativeQuery = true)
    List<CajaIngredientes> getCajaIngredByIdOcupacionCaja(Integer id_ocupacion_caja);
}
