import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  registros: any[] = [];
  registroPresos: any[] = [];
  registroBodega: any[] = [];
  loading = false;
  displayedColumns: string[] = [
    'id', 'nombre', 'dias', 'fecha_inicio', 'fecha_fin', 
    'es_premium', 'presupuesto', 'perdida', 
    'registroPresos', 'registroBodega'
  ];

  simulacionForm = this.fb.group({
    idRegistroPresos: [null, Validators.required],
    idBodega: [null, Validators.required],
    esPremium: [false],
    dias: [7, Validators.required],
    fechaInicio: ['', Validators.required]
  });

  constructor(private api: ApiService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadRegistros();
    this.loadRegistroBodega();
    this.loadRegistroPresos();
  }

  loadRegistros() {
    this.api.getRegistroSimulacionAll().subscribe({
      next: (res:any) => { this.registros = res?.body || res || []; },
      error: (err)=> { console.error(err); }
    });
  }

  loadRegistroPresos() {
    this.api.getAllRegistroPresos().subscribe({
      next: (res:any) => { this.registroPresos = res?.body || res || []; },
      error: console.error
    });
  }

  loadRegistroBodega() {
    this.api.getAllRegistroBodega().subscribe({
      next: (res:any) => { this.registroBodega = res?.body || res || []; },
      error: console.error
    });
  }

  crearSimulacion() {
    if (this.simulacionForm.invalid) return;

    const body = {
      idRegistroPresos: this.simulacionForm.value.idRegistroPresos,
      idBodega: this.simulacionForm.value.idBodega,
      esPremium: this.simulacionForm.value.esPremium,
      dias: this.simulacionForm.value.dias,
      fechaInicio: this.simulacionForm.value.fechaInicio
    };

    this.loading = true;
    this.api.runSimulacion(body).subscribe({
      next: () => { 
        this.loading = false; 
        this.simulacionForm.reset({ dias: 7, esPremium: false });
        this.loadRegistros(); 
      },
      error: () => { this.loading = false; }
    });
  }

  runSimulacion() {
    this.loading = true;
    this.api.runSimulacion({}).subscribe({
      next: (res)=> { this.loading = false; this.loadRegistros(); },
      error: ()=> { this.loading = false; }
    });
  }

}
