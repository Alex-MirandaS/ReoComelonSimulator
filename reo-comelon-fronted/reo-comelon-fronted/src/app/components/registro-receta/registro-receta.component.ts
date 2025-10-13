import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-registro-receta',
  templateUrl: './registro-receta.component.html'
})
export class RegistroRecetaComponent implements OnInit {
  recetaForm = this.fb.group({
    id_Tipo_Receta: [null, Validators.required],
    nombre: ['', Validators.required],
    es_premium: [false]
  });

  recetaIngredForm = this.fb.group({
    id_receta: [null, Validators.required],
    id_ingrediente: [null, Validators.required],
    cantidad: [1, Validators.required]
  });

  recetas:any[] = [];
  ingredientesDetalle: any[] = []; 
  tiposReceta: any[] = [];
  ingredientes: any[] = [];
  
  constructor(private fb: FormBuilder, private api: ApiService){}

  ngOnInit(): void { this.loadAll(); this.loadIngredientes(); this.loadTiposReceta();}

  submitReceta() {
    if (this.recetaForm.invalid) return;
  
    this.api.createReceta(this.recetaForm.value).subscribe({
      next: () => {
        this.loadAll();              // recarga la lista de recetas
        this.recetaForm.reset();     // limpia todos los campos
        this.recetaForm.get('es_premium')?.setValue(false); // opcional: reset valor booleano
      },
      error: console.error
    });
  }
  

  submitRecetaIngred() {
    if (this.recetaIngredForm.invalid) return;
  
    this.api.createRecetaIngrediente(this.recetaIngredForm.value).subscribe({
      next: () => {
        this.recetaIngredForm.reset();  // limpia campos
        this.recetaIngredForm.get('cantidad')?.setValue(0); // opcional: valor predeterminado para cantidad
      },
      error: console.error
    });
  }
  

  loadAll() {
    this.api.getAllReceta().subscribe({ next: (res:any)=> this.recetas = res?.body || res || [], error: console.error });
  }

  loadTiposReceta() {
    this.api.getAllTipoReceta().subscribe({ next: (res:any) => this.tiposReceta = res?.body || res || [], error: console.error });
  }

  loadIngredientes() {
    this.api.getAllIngredientes().subscribe({ next: (res:any) => this.ingredientes = res?.body || res || [], error: console.error });
  }

  verDetalleReceta(idReceta: number) {
    this.api.getIngredientesPorReceta(idReceta).subscribe({
      next: (res: any) => this.ingredientesDetalle = res?.body || res || [],
      error: console.error
    });
  }
}
