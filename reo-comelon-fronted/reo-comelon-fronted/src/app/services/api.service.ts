import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class ApiService {
  private base = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  //SIMULACION
  runSimulacion(body?: any): Observable<any> {
    return this.http.post(`${this.base}/simulacion/run`, body || {});
  }
  
  getRegistroSimulacionAll(): Observable<any> {
    return this.http.get(`${this.base}/registroSimulacion/all`);
  }

  // Registro Presos
  createRegistroPresos(body: any) { return this.http.post(`${this.base}/registroPresos`, body); }
  getAllRegistroPresos() { return this.http.get(`${this.base}/registroPresos/all`); }
  getBitacoraByRegistroPresos(id: number) { return this.http.get(`${this.base}/bitacoraRegistroPresos/registroPresos/${id}`); }

  //Tipo Preso
  getAllTipoPreso() { return this.http.get(`${this.base}/tipoPreso/all`); }
  //Ocupación de Caja
  getAllOcupacionCaja() { return this.http.get(`${this.base}/ocupacionCaja/all`); }
  //RecetaIngrediente
  getIngredientesPorReceta(idReceta: number) {
    return this.http.get(`${this.base}/recetaIngrediente/receta/${idReceta}`);
  }

  // Registro Bodega
  createRegistroBodega(body: any) { return this.http.post(`${this.base}/registroBodega`, body); }
  getAllRegistroBodega() { return this.http.get(`${this.base}/registroBodega/all`); }

  // Ingredientes y CajaIngredientes
  createIngrediente(body: any) { return this.http.post(`${this.base}/ingrediente`, body); }
  createCajaIngredientes(body: any) { return this.http.post(`${this.base}/cajaIngredientes`, body); }
  getAllIngredientes() { return this.http.get(`${this.base}/ingrediente/all`); }
  getAllCajaIngredientes() { return this.http.get(`${this.base}/cajaIngredientes/all`); }

  // Receta y RecetaIngrediente
  createReceta(body: any) { return this.http.post(`${this.base}/receta`, body); }
  createRecetaIngrediente(body: any) { return this.http.post(`${this.base}/recetaIngrediente`, body); }
  getAllReceta() { return this.http.get(`${this.base}/receta/all`); }
  // Tipo Receta
  getAllTipoReceta() { return this.http.get(`${this.base}/tipoReceta/all`); }
}
