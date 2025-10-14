package modela1.reo_comelon_simulator.repository.crud;

import modela1.reo_comelon_simulator.repository.entities.RegistroCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroComprasCrud extends JpaRepository<RegistroCompras, Integer> {

    @Query(value = "SELECT * FROM registro_compras WHERE id_simulacion = ?", nativeQuery = true)
    List<RegistroCompras> getByIdSimulacion(Integer id_simulacion);

    @Query(value = "SELECT * FROM registro_compras WHERE id_caja_ingredientes = ?", nativeQuery = true)
    List<RegistroCompras> getByIdCajaIngredientes(Integer id_caja_ingredientes);
}
