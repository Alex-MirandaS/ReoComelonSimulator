import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-registro-presos',
  templateUrl: './registro-presos.component.html'
})
export class RegistroPresosComponent implements OnInit {
  registroForm = this.fb.group({
    id_tipo_preso: [null, Validators.required],
    cantidad: [1, [Validators.required]],
    activo: [true]
  });
  registros:any[] = [];
  tiposPreso:any[] = [];

  constructor(private fb: FormBuilder, private api: ApiService) {}

  ngOnInit(): void { this.loadAll();   this.loadTiposPreso(); }

  submit() {
    if (this.registroForm.invalid) return;
    this.api.createRegistroPresos(this.registroForm.value).subscribe({
      next: ()=> { this.loadAll(); this.registroForm.reset({cantidad:1, activo:true}); },
      error: (e)=>console.error(e)
    });
  }

  loadAll() {
    this.api.getAllRegistroPresos().subscribe({
      next: (res: any) => {
        this.registros = res?.body || res || [];
      },
      error: (err) => {
        console.error('Error cargando presos:', err);
      }
    });
  }

  loadTiposPreso() {
    this.api.getAllTipoPreso().subscribe({
      next: (res: any) => {
        // Extraemos solo el array dentro de 'body'
        this.tiposPreso = res.body || [];
        //console.log('Tipos de preso cargados:', this.tiposPreso);
      },
      error: (err) => console.error('Error cargando tipos de preso:', err)
    });
  }
  
  

  viewBitacora(id:number) {
    this.api.getBitacoraByRegistroPresos(id).subscribe({
      next: (res:any)=> { const body = res?.body || res || []; alert(JSON.stringify(body)); },
      error: console.error
    });
  }
}
