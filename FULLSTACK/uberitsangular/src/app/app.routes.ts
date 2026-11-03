import { Routes } from '@angular/router';
import { Principal } from './principal/principal';
import { Login } from './login/login';
import { Registro } from './registro/registro';
import { RestauranteDetalle } from './restaurante-detalle/restaurante-detalle';

export const routes: Routes = [
  { path: '', component: Principal },
  { path: 'restaurante/:id', component: RestauranteDetalle },
  { path: 'login', component: Login },
  { path: 'registro', component: Registro },
];
