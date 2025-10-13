import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegistroPresosComponent } from './components/registro-presos/registro-presos.component';
import { RegistroBodegaComponent } from './components/registro-bodega/registro-bodega.component';
import { RegistroIngredientesComponent } from './components/registro-ingredientes/registro-ingredientes.component';
import { RegistroRecetaComponent } from './components/registro-receta/registro-receta.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'registro-presos', component: RegistroPresosComponent },
  { path: 'registro-bodega', component: RegistroBodegaComponent },
  { path: 'registro-ingredientes', component: RegistroIngredientesComponent },
  { path: 'registro-receta', component: RegistroRecetaComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}