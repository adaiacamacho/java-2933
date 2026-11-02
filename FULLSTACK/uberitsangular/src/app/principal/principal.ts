import { Component } from '@angular/core';
import { RestauranteListado } from '../restaurante-listado';

@Component({
  imports: [],
  selector: 'app-principal',
  styleUrl: './principal.css',
  templateUrl: './principal.html',
})
export class Principal {
  restaurantes: RestauranteListado[] = [
    { id: 1, nombre: 'TeleBurger', tiposComida: ['Americana', 'Basura'] },
    { id: 2, nombre: 'Pizza A Domicilio', tiposComida: ['Italiana', 'Basura'] },
  ];
}
