import { Routes } from '@angular/router';
import { Principal } from './principal/principal';
import { Login } from './login/login';
import { Registro } from './registro/registro';

export const routes: Routes = [
  { path: '', component: Principal },
  { path: 'login', component: Login },
  { path: 'registro', component: Registro },
];
