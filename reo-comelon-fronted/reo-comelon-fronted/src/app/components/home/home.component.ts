import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  simulacionForm = this.fb.group({
    idRegistroPresos: [null, Validators.required],
    idBodega: [null, Validators.required],
    dias: [1, [Validators.required, Validators.min(1)]],
    fechaInicio: [null, Validators.required],
    esPremium: [false],
    optimizado: [false]
  });
  comprasSimulacion: any[] = [];
  registros: any[] = [];
  registroPresos: any[] = [];
  registroBodega: any[] = [];
  resultadoSimulacion: any = null;
  loading = false;
  menusSimulacion: any[] = [];  
  simulacionSeleccionada: any = null; 
  recetasPorMenu: { [menuId: number]: any[] } = {}; 

  displayedColumns: string[] = [
    'id', 'nombre', 'dias', 'fecha_inicio', 'es_premium', 'presupuesto', 'perdida', 'detalles'
  ];

  constructor(private fb: FormBuilder, private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadRegistros();
    this.loadRegistroPresos();
    this.loadRegistroBodega();
  }

  loadRegistros(): void {
    this.apiService.getRegistroSimulacionAll().subscribe({
      next: (res: any) => {
        this.registros = res.body || [];
      },
      error: (err) => console.error('Error cargando simulaciones', err)
    });
  }

  loadRegistroPresos(): void {
    this.apiService.getAllRegistroPresos().subscribe({
      next: (res: any) => {
        this.registroPresos = res.body || [];
      },
      error: (err) => console.error('Error cargando registro presos', err)
    });
  }

  loadRegistroBodega(): void {
    this.apiService.getAllRegistroBodega().subscribe({
      next: (res: any) => {
        this.registroBodega = res.body || [];
      },
      error: (err) => console.error('Error cargando bodega', err)
    });
  }

  crearSimulacion(): void {
    if (this.simulacionForm.invalid) return;
  
    this.loading = true;
    this.apiService.runSimulacion(this.simulacionForm.value).subscribe({
      next: (res: any) => {
        this.loading = false;
        this.resultadoSimulacion = res.body;
        this.simulacionSeleccionada = res.body;
  
        alert(
          `✅ Simulación generada exitosamente\n\n` +
          `Costo Total: ${res.body.costoTotal}\n` +
          `Espacio Usado: ${res.body.espacioUsado}\n` +
          `Espacio Disponible: ${res.body.espacioDisponible}\n` +
          `Pérdida: ${res.body.perdida}`
        );
  
        this.loadRegistros();
  
        // 👇 Aquí está bien colocada la llamada
        if (res.body.idSimulacion) {
          this.apiService.getComprasByIdSimulador(res.body.idSimulacion).subscribe({
            next: (resCompras: any) => {
              this.comprasSimulacion = resCompras.body || [];
              console.log('Compras de la simulación', this.comprasSimulacion);
            },
            error: (err) => console.error('Error cargando compras post-simulación', err)
          });
        }
  
        console.log('Resultado completo:', res);
      },
      error: (err) => {
        console.error('Error creando simulación', err);
        this.loading = false;
        alert('❌ Error al generar la simulación');
      }
    });
  }
  
  
  verDetalles(simulacion: any): void {
    this.simulacionSeleccionada = simulacion;
    this.menusSimulacion = [];
    this.recetasPorMenu = {};

    this.apiService.getMenuByIdSimulacion(simulacion.id).subscribe({
      next: (res: any) => {
        this.menusSimulacion = res.body || [];
        console.log('Menús de la simulación', this.menusSimulacion);

        // 👇 Por cada menú, obtenemos sus recetas
        this.menusSimulacion.forEach((menu) => {
          this.apiService.getRecetaByIdMenu(menu.id).subscribe({
            next: (resReceta: any) => {
              this.recetasPorMenu[menu.id] = resReceta.body || [];
            },
            error: (err) => console.error(`Error obteniendo recetas del menú ${menu.id}`, err)
          });
        });
      },
      error: (err) => {
        console.error('Error obteniendo menús', err);
        alert('❌ Error al obtener los menús de la simulación');
      }
    });
  }
}
