package modela1.reo_comelon_simulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modela1.reo_comelon_simulator.dto.request.NewDetalleMenuDto;
import modela1.reo_comelon_simulator.dto.request.NewMenuDto;
import modela1.reo_comelon_simulator.dto.request.NewRegistroComprasDto;
import modela1.reo_comelon_simulator.dto.request.NewSimulacionRequestDto;
import modela1.reo_comelon_simulator.dto.response.MenuDto;
import modela1.reo_comelon_simulator.dto.response.ResponseSuccessfullyDto;
import modela1.reo_comelon_simulator.dto.response.SimulacionResultadoDto;
import modela1.reo_comelon_simulator.exception.BusinessException;
import modela1.reo_comelon_simulator.repository.crud.*;
import modela1.reo_comelon_simulator.repository.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class SimulacionService {
    private final RecetaService recetaService;
    private final RecetaIngredienteService recetaIngredienteService;
    private final IngredienteService ingredienteService;
    private final MenuService menuService;
    private final DetalleMenuService detalleMenuService;
    private final RegistroBodegaService registroBodegaService;
    private final RegistroPresosService registroPresosService;
    private final RegistroSimulacionService registroSimulacionService;
    private final TipoPresoService tipoPresoService;
    private final CajaIngredientesService cajaIngredientesService;
    private final RegistroSimulacionCrud registroSimulacionCrud;
    private final SimulacionMenusCrud simulacionMenusCrud;
    private final RegistroComprasService registroComprasService;
    private final RegistroComprasCrud registroComprasCrud;
    private final BitacoraRegistroBodegaCrud bitacoraRegistroBodegaCrud;

    public ResponseSuccessfullyDto runSimulacion(NewSimulacionRequestDto dto) {
        //Obtener Registros de Presos y Espacio en Bodega
        RegistroPresos registroPresos = registroPresosService.getRegistroPresosByIdRegistroPresos(dto.getIdRegistroPresos());
        RegistroBodega bodega = registroBodegaService.getRegistroBodegaByIdRegistroBodega(dto.getIdBodega());
        int cantidadReos = registroPresos.getCantidad();
        //Genera los menús automáticos
        List<Menu> menus = generarMenusAutomaticos(dto.getDias(), dto.getEsPremium(), dto.getOptimizado());
        //Genera los Ingredientes totales
        List<Ingrediente> ingredientes = calcularIngredientesTotales(menus, cantidadReos);

        double espacioUsado = calcularEspacioUsado(ingredientes);
        double espacioDisponible = bodega.getCapacidad();
        double costoTotal = calcularCostoTotal(ingredientes);
        LocalDate fechaFin = dto.getFechaInicio().plusDays(dto.getDias());
        RegistroSimulacion simulacion = new RegistroSimulacion();
        simulacion.setNombre("Simulación "+dto.getFechaInicio()+"-"+fechaFin);
        simulacion.setRegistroPresos(registroPresosService.getRegistroPresosByIdRegistroPresos(dto.getIdRegistroPresos()));
        simulacion.setRegistroBodega(registroBodegaService.getRegistroBodegaByIdRegistroBodega(dto.getIdBodega()));
        simulacion.setDias(dto.getDias());
        simulacion.setFecha_inicio(dto.getFechaInicio());
        simulacion.setFecha_fin(fechaFin);
        simulacion.setEs_premium(dto.getEsPremium());
        simulacion.setPresupuesto(costoTotal);
        //verificar
        simulacion.setPerdida(espacioUsado > espacioDisponible ? costoTotal * 0.05 : 0.0);
        //generarRegistroCompras(simulacion, ingredientes);
        registroSimulacionCrud.save(simulacion);

        for (Menu menu : menus) {
            SimulacionMenus sm = new SimulacionMenus();
            sm.setRegistroSimulacion(registroSimulacionService.getRegistroSimulacionByIdRegistroSimulacion(simulacion.getId()));
            sm.setMenu(menuService.getMenuByIdMenu(menu.getId()));
            simulacionMenusCrud.save(sm);
        }

        generarRegistroComprasYBitacora(simulacion, menus, cantidadReos, bodega);

        SimulacionResultadoDto resultado = SimulacionResultadoDto.builder().idSimulacion(simulacion.getId()).costoTotal(costoTotal).espacioUsado(espacioUsado).espacioDisponible(espacioDisponible).perdida(simulacion.getPerdida()).menusGenerados(menus).ingredientesRequeridos(ingredientes).build();

        return ResponseSuccessfullyDto.builder()
                .code(HttpStatus.OK)
                .message("Simulación generada exitosamente")
                .body(resultado)
                .build();
    }

    private List<Menu> generarMenusAutomaticos(int dias, boolean esPremium, boolean esOptimizado) {
        try {
            //Listar Recetas, según su optimización
            List<Receta> recetasDesayuno = recetaService.getRecetaByIdesPremiunIdTipoReceta(esPremium,1);
            List<Receta> recetasAlmuerzo = recetaService.getRecetaByIdesPremiunIdTipoReceta(esPremium,2);
            List<Receta> recetasCena = recetaService.getRecetaByIdesPremiunIdTipoReceta(esPremium,3);
            if(esOptimizado){
                recetasDesayuno = recetaService.getRecetaByIdTipoRecetaAndEsPremium(esPremium,1);
                recetasAlmuerzo = recetaService.getRecetaByIdTipoRecetaAndEsPremium(esPremium,2);
                recetasCena = recetaService.getRecetaByIdTipoRecetaAndEsPremium(esPremium,3);
            }

            if (recetasDesayuno == null || recetasDesayuno.isEmpty() || recetasAlmuerzo == null || recetasAlmuerzo.isEmpty() || recetasCena == null || recetasCena.isEmpty()) {
                throw new BusinessException(HttpStatus.NOT_FOUND, "No existen recetas para generar menús");
            }

            List<Menu> menusGenerados = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < dias; i++) {
                //CAMBIAR A CALCULO DE OPTIMIZACIÓN
                Receta desayuno = null;
                Receta almuerzo = null;
                Receta cena = null;
                if(esOptimizado){
                    // Elegir 3 recetas (las que contengan menor cantidad de ingrediente y menor gasto total) para desayuno, almuerzo y cena (La misma para todos los días)
                    desayuno = recetasDesayuno.get(0);
                    almuerzo = recetasAlmuerzo.get(0);
                    cena = recetasCena.get(0);
                }else{
                    // Elegir 3 recetas al azar para desayuno, almuerzo y cena
                    desayuno = recetasDesayuno.get(random.nextInt(recetasDesayuno.size()));
                    almuerzo = recetasAlmuerzo.get(random.nextInt(recetasAlmuerzo.size()));
                    cena = recetasCena.get(random.nextInt(recetasCena.size()));
                }


                // Crear el menú
                Menu menu = new Menu();
                menu.setFecha(LocalDate.now().plusDays(i));
                // tipo_preso: 1 = normal, 2 = premium (suposición)
                menu.setTipoPreso(esPremium
                        ? tipoPresoService.getTipoPresoByIdTipoPreso(2)
                        : tipoPresoService.getTipoPresoByIdTipoPreso(1));

                // Guardar menú
                Menu menuSaved = menuService.createMenuAndReturn(
                        NewMenuDto.builder()
                                .id_tipo_preso(menu.getTipoPreso().getId())
                                .fecha(menu.getFecha())
                                .build()
                );

                // Guardar detalle del menú
                DetalleMenu detalle = new DetalleMenu();
                detalle.setMenu(menuSaved);
                detalle.setReceta_des(desayuno);
                detalle.setReceta_lunch(almuerzo);
                detalle.setReceta_cena(cena);
                detalleMenuService.createDetalleMenu(
                        NewDetalleMenuDto.builder().id_menu(detalle.getMenu().getId()).id_receta_des(detalle.getReceta_des().getId()).id_receta_lunch(detalle.getReceta_lunch().getId()).id_receta_cena(detalle.getReceta_cena().getId()).build()
                );
                menusGenerados.add(menuSaved);
            }

            return menusGenerados;

        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al generar los menús automáticamente");
        }
    }

    private List<Ingrediente> calcularIngredientesTotales(List<Menu> menus, int cantidadReos) {
        // Mapa para acumular ingredientes y su cantidad total
        Map<Integer, Ingrediente > acumulador = new HashMap<>();

        for (Menu menu : menus) {
            // Obtener detalle del menú (desayuno, lunch, cena)
            List<DetalleMenu> detalles = detalleMenuService.getDetalleMenuByIdMenuList(menu.getId());
            for (DetalleMenu detalle : detalles) {

                // Recorrer recetas del detalle
                List<Receta> recetas = Arrays.asList(
                        detalle.getReceta_des(),
                        detalle.getReceta_lunch(),
                        detalle.getReceta_cena()
                );

                for (Receta receta : recetas) {
                    List<RecetaIngrediente> recetaIngredientes =
                            recetaIngredienteService.getRecetaIngredByIdRecetaObj(receta.getId());

                    for (RecetaIngrediente ri : recetaIngredientes) {
                        Ingrediente ing = ingredienteService.getIngredienteByIdIngrediente(ri.getIngrediente().getId());
                        int cantidadTotal = ri.getCantidad() * cantidadReos;

                        // Si ya existe el ingrediente, solo acumula la cantidad
                        if (acumulador.containsKey(ing.getId())) {
                            Ingrediente existente = acumulador.get(ing.getId());
                            existente.setCantidad(existente.getCantidad() + cantidadTotal);
                        } else {
                            // Si no existe, clónalo y asigna la cantidad inicial
                            Ingrediente nuevo = new Ingrediente();
                            nuevo.setId(ing.getId());
                            nuevo.setIngrediente(ing.getIngrediente());
                            nuevo.setVida_util_dias(ing.getVida_util_dias());
                            nuevo.setCantidad(cantidadTotal);
                            acumulador.put(ing.getId(), nuevo);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(acumulador.values());
    }



    private double calcularEspacioUsado(List<Ingrediente> ingredientes) {
        double espacioTotal = 0.0;

        for (Ingrediente ingrediente : ingredientes) {
            // Obtener cajas de ese ingrediente
            List<CajaIngredientes> cajas = cajaIngredientesService.getAllCajaIngredientesListByIdIngrediente(ingrediente.getId());
            //Calcular cantidad de cajas necesarias:
            double cantidadCajas = Math.ceil((double)ingrediente.getCantidad()/cajas.get(0).getCantidad());

            // La ocupacion de cada caja está en m³, entonces para calcular la cantidad total de todas las cajas de cada ingrediente, se calcula así
            double espacioIngrediente = cajas.get(0).getOcupacionCaja().getOcupacion() * cantidadCajas;

            espacioTotal += espacioIngrediente;
        }

        return espacioTotal;
    }
//calcular cajas
    private double calcularCostoTotal(List<Ingrediente> ingredientes) {
        double total = 0.0;
        for (Ingrediente ingrediente : ingredientes) {
            List<CajaIngredientes> cajas = cajaIngredientesService.getAllCajaIngredientesListByIdIngrediente(ingrediente.getId());
            //Calcular cantidad de cajas necesarias:
            double cantidadCajas = Math.ceil((double)ingrediente.getCantidad()/cajas.get(0).getCantidad());
                total += cajas.get(0).getPrecio() * cantidadCajas;
        }

        return total;
    }
    private void generarRegistroComprasYBitacora(RegistroSimulacion simulacion, List<Menu> menus, int cantidadReos, RegistroBodega bodega) {
        // DEMANDA POR DIA
        Map<LocalDate, Map<Integer, Integer>> demandaPorDia = new HashMap<>();
        for (Menu menu : menus) {
            LocalDate fecha = menu.getFecha();
            List<DetalleMenu> detalles = detalleMenuService.getDetalleMenuByIdMenuList(menu.getId());
            Map<Integer, Integer> demandaDia = demandaPorDia.computeIfAbsent(fecha, d -> new HashMap<>());
            for (DetalleMenu detalle : detalles) {
                List<Receta> recetas = Arrays.asList(detalle.getReceta_des(), detalle.getReceta_lunch(), detalle.getReceta_cena());
                for (Receta receta : recetas) {
                    List<RecetaIngrediente> recetaIngredientes = recetaIngredienteService.getRecetaIngredByIdRecetaObj(receta.getId());
                    for (RecetaIngrediente ri : recetaIngredientes) {
                        int idIng = ri.getIngrediente().getId();
                        int unidades = ri.getCantidad() * cantidadReos;
                        demandaDia.put(idIng, demandaDia.getOrDefault(idIng, 0) + unidades);
                    }
                }
            }
        }

        // 2) Batches por ingrediente (FIFO). Batch almacena unidades y volumen ocupado.
        class Batch {
            LocalDate compra;
            LocalDate vencimiento;
            int unidades;
            double volumen; // m3
            Batch(LocalDate compra, LocalDate vencimiento, int unidades, double volumen){
                this.compra = compra; this.vencimiento = vencimiento; this.unidades = unidades; this.volumen = volumen;
            }
        }
        Map<Integer, Deque<Batch>> inventario = new HashMap<>();

        LocalDate fechaInicio = simulacion.getFecha_inicio();
        LocalDate fechaFin = simulacion.getFecha_fin(); // nota: en tu código fechaFin = inicio + dias

        int totalDias = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        // Si deseas incluir fechaFin como día final, usar <=; aquí iteramos 0..dias-1
        for (int d = 0; d < totalDias; d++) {
            LocalDate hoy = fechaInicio.plusDays(d);

            // Antes de procesar demanda, eliminar batches vencidos (vencimiento < hoy)
            for (Integer idIng : new HashSet<>(inventario.keySet())) {
                Deque<Batch> cola = inventario.get(idIng);
                if (cola == null) continue;
                while (!cola.isEmpty() && (cola.peekFirst().vencimiento.isBefore(hoy) )) {
                    cola.pollFirst();
                }
                if (cola.isEmpty()) inventario.remove(idIng);
            }

            // Calcular volumen actualmente ocupado en bodega (sumar volumen de todos batches)
            double volumenOcupado = 0.0;
            for (Deque<Batch> cola : inventario.values()) {
                for (Batch b : cola) volumenOcupado += b.volumen;
            }

            // Procesar compra y consumo por cada ingrediente que aparece en la simulación (o en la demanda)
            Set<Integer> ingredientesInvolucrados = new HashSet<>();
            ingredientesInvolucrados.addAll(demandaPorDia.getOrDefault(hoy, Collections.emptyMap()).keySet());
            // También considerar ingredientes de inventario (posible expiración)
            ingredientesInvolucrados.addAll(inventario.keySet());

            for (Integer idIngrediente : ingredientesInvolucrados) {
                int demandaHoy = demandaPorDia.getOrDefault(hoy, Collections.emptyMap()).getOrDefault(idIngrediente, 0);

                // sumar unidades disponibles
                int disponibles = 0;
                Deque<Batch> pila = inventario.getOrDefault(idIngrediente, new ArrayDeque<>());
                for (Batch b : pila) disponibles += b.unidades;

                // Si hay suficiente, consumir FIFO
                int aConsumir = demandaHoy;
                while (aConsumir > 0 && !pila.isEmpty()) {
                    Batch first = pila.peekFirst();
                    int take = Math.min(first.unidades, aConsumir);
                    first.unidades -= take;
                    aConsumir -= take;
                    if (first.unidades == 0) pila.pollFirst();
                }
                // actualizar inventario mapa
                if (!pila.isEmpty()) inventario.put(idIngrediente, pila); else inventario.remove(idIngrediente);

                // Si faltó para cubrir la demanda, planificar compra para el mismo día (según vida útil)
                if (aConsumir > 0) {
                    // obtener datos de caja para ese ingrediente (asumimos al menos una caja disponible y tomamos la primera)
                    List<CajaIngredientes> cajas = cajaIngredientesService.getAllCajaIngredientesListByIdIngrediente(idIngrediente);
                    if (cajas == null || cajas.isEmpty()) {
                        // no hay caja disponible para ese ingrediente, no se puede comprar -> saltar
                        continue;
                    }
                    CajaIngredientes caja = cajas.get(0);
                    int unidadesPorCaja = caja.getCantidad();
                    double volumenPorCaja = caja.getOcupacionCaja().getOcupacion(); // m3 por caja
                    Ingrediente ing = ingredienteService.getIngredienteByIdIngrediente(idIngrediente);
                    int vidaUtil = ing.getVida_util_dias();

                    // unidades necesarias después de lo ya consumido
                    int unidadesNecesarias = aConsumir;

                    // calcular cajas necesarias
                    int cajasNecesarias = (int) Math.ceil((double) unidadesNecesarias / unidadesPorCaja);

                    // verificar espacio en bodega: ¿caben cajasNecesarias?
                    double espacioExtra = cajasNecesarias * volumenPorCaja;
                    double espacioLibre = bodega.getCapacidad() - volumenOcupado;

                    int cajasAComprar = cajasNecesarias;
                    if (espacioExtra > espacioLibre) {
                        // intentar comprar menos cajas que quepan
                        int maxCajasQueCabes = (int) Math.floor(espacioLibre / volumenPorCaja);
                        if (maxCajasQueCabes <= 0) {
                            // no cabe ninguna caja: no comprar
                            cajasAComprar = 0;
                        } else {
                            cajasAComprar = Math.min(maxCajasQueCabes, cajasNecesarias);
                        }
                    }

                    if (cajasAComprar > 0) {
                        int unidadesCompradas = cajasAComprar * unidadesPorCaja;
                        LocalDate fechaVenc = hoy.plusDays(vidaUtil);

                        // Crear y persistir registro de compra
                        RegistroCompras compra = new RegistroCompras();
                        compra.setRegistroSimulacion(simulacion);
                        compra.setCajaIngredientes(caja);
                        compra.setCantidad((double) unidadesCompradas); // guardamos unidades
                        compra.setFechaCompra(hoy);
                        compra.setFechaVencimiento(fechaVenc);
                        registroComprasCrud.save(compra);
                        // añadir batch al inventario
                        double volumenComprado = cajasAComprar * volumenPorCaja;
                        Deque<Batch> cola = inventario.computeIfAbsent(idIngrediente, k -> new ArrayDeque<>());
                        cola.addLast(new Batch(hoy, fechaVenc, unidadesCompradas, volumenComprado));
                        volumenOcupado += volumenComprado;

                        // Consumir inmediatamente las unidades necesarias (si aún quedan)
                        int pendiente = aConsumir - unidadesCompradas;
                        if (pendiente < 0) pendiente = 0;
                        // Consumir de los batches recién añadidos (ya que los hemos puesto al final)
                        int aQuitar = Math.min(unidadesCompradas, aConsumir);
                        int remainToConsume = aQuitar;
                        while (remainToConsume > 0 && !cola.isEmpty()) {
                            Batch last = cola.peekLast();
                            int take = Math.min(last.unidades, remainToConsume);
                            last.unidades -= take;
                            remainToConsume -= take;
                            if (last.unidades == 0) cola.pollLast();
                        }
                        if (!cola.isEmpty()) inventario.put(idIngrediente, cola); else inventario.remove(idIngrediente);
                    } else {
                        // No se pudo comprar nada; opcional: registrar intento de compra fallido (no implementado)
                    }
                }
            }

            // Al terminar el dia, recalcular volumen ocupado
            double volumenAlFinalDia = 0.0;
            for (Deque<Batch> cola : inventario.values()) for (Batch b : cola) volumenAlFinalDia += b.volumen;

            double capacidadLibre = bodega.getCapacidad() - volumenAlFinalDia;
            if (capacidadLibre < 0) capacidadLibre = 0.0;

            // Persistir entrada en bitacora_registro_bodega
            BitacoraRegistroBodega bit = new BitacoraRegistroBodega();
            bit.setRegistroBodega(bodega);
            bit.setCapacidadLibre((int)Math.floor(capacidadLibre)); // si quieres double, adapta tipo
            bit.setCapacidadUtilizada(volumenAlFinalDia);
            bit.setFecha(hoy);
            bitacoraRegistroBodegaCrud.save(bit);
        }
    }

}