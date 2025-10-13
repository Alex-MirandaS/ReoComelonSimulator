import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-registro-bodega',
  templateUrl: './registro-bodega.component.html'
})
export class RegistroBodegaComponent implements OnInit {
  form = this.fb.group({
    bodega: ['', Validators.required],
    capacidad: [0, Validators.required]
  });
  list:any[] = [];

  constructor(private fb: FormBuilder, private api: ApiService){}

  ngOnInit(): void { this.loadAll(); }

  submit() {
    if (this.form.invalid) return;
    this.api.createRegistroBodega(this.form.value).subscribe({ next: ()=>{ this.loadAll(); this.form.reset(); }, error: console.error });
  }

  loadAll() {
    this.api.getAllRegistroBodega().subscribe({ next: (res:any)=> this.list = res?.body || res || [], error: console.error });
  }
}
