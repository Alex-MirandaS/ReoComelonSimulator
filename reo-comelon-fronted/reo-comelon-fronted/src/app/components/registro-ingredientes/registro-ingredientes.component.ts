import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-registro-ingredientes',
  templateUrl: './registro-ingredientes.component.html'
})
export class RegistroIngredientesComponent implements OnInit {

  ingredienteForm = this.fb.group({
    // Ingrediente
    ingrediente: ['', Validators.required],
    vida_util_dias: [0, Validators.required],
    // Caja
    id_ocupacion_caja: [null, Validators.required],
    cantidad: [0, Validators.required],
    precio: [0, Validators.required]
  });

  ingredientes: any[] = [];
  cajas: any[] = [];
  ocupacionesCaja: any[] = [];

  constructor(private fb: FormBuilder, private api: ApiService) {}

  ngOnInit(): void {
    this.loadAll();
    this.loadOcupacionesCaja();
  }

  loadOcupacionesCaja() {
    this.api.getAllOcupacionCaja().subscribe({
      next: (res: any) => this.ocupacionesCaja = res?.body || res || [],
      error: console.error
    });
  }

  submitBoth() {
    if (this.ingredienteForm.invalid) return;

    // Crear ingrediente
    const ingredienteBody = {
      ingrediente: this.ingredienteForm.value.ingrediente,
      vida_util_dias: this.ingredienteForm.value.vida_util_dias,
      cantidad: 0 
    };

    this.api.createIngrediente(ingredienteBody).subscribe({
      next: (res: any) => {
        console.log(res);
        const idIngrediente = res?.body?.id || null;
        if (idIngrediente) {
          // Crear caja usando el id del ingrediente recién creado
          const cajaBody = {
            id_ocupacion_caja: this.ingredienteForm.value.id_ocupacion_caja,
            cantidad: this.ingredienteForm.value.cantidad,
            precio: this.ingredienteForm.value.precio,
            id_ingrediente: idIngrediente
          };
          this.api.createCajaIngredientes(cajaBody).subscribe({
            next: () => {
              this.loadAll();
              this.ingredienteForm.reset();
            },
            error: console.error
          });
        } else {
          alert('Ingrediente creado, pero no se obtuvo su id. Crea la caja manualmente.');
          this.loadAll();
        }
      },
      error: console.error
    });
  }

  loadAll() {
    this.api.getAllIngredientes().subscribe({
      next: (res: any) => this.ingredientes = res?.body || res || [],
      error: console.error
    });
    this.api.getAllCajaIngredientes().subscribe({
      next: (res: any) => this.cajas = res?.body || res || [],
      error: console.error
    });
  }

}
