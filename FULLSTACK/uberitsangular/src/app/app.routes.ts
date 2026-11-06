import { Routes } from '@angular/router';
import { Principal } from './principal/principal';
import { Login } from './login/login';
import { Registro } from './registro/registro';
import { RestauranteDetalle } from './restaurante-detalle/restaurante-detalle';
import { autenticadoGuard } from './autenticado-guard';
import { adminGuard } from './admin-guard';
import { AdminRestaurantes } from './admin-restaurantes/admin-restaurantes';
import { AdminRestaurante } from './admin-restaurante/admin-restaurante';

export const routes: Routes = [
  { path: '', component: Principal },
  { path: 'restaurante/:id', component: RestauranteDetalle, canActivate: [autenticadoGuard] },
  { path: 'login', component: Login },
  { path: 'registro', component: Registro },
  {
    path: 'admin',
    canActivate: [adminGuard],
    children: [
      { path: 'restaurantes', component: AdminRestaurantes },
      { path: 'restaurante', component: AdminRestaurante },
    ],
  },
];
