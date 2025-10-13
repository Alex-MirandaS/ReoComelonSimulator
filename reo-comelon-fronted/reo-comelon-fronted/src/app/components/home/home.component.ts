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

  registros: any[] = [];
  registroPresos: any[] = [];
  registroBodega: any[] = [];
  resultadoSimulacion: any = null;
  loading = false;

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

        alert(
          `✅ Simulación generada exitosamente\n\n` +
          `Costo Total: ${res.body.costoTotal}\n` +
          `Espacio Usado: ${res.body.espacioUsado}\n` +
          `Espacio Disponible: ${res.body.espacioDisponible}\n` +
          `Pérdida: ${res.body.perdida}`
        );

        this.loadRegistros();
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
    alert(`Detalles de simulación ID: ${simulacion.id}`);
  }
}
