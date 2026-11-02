import { Service } from '@angular/core';
import { RestauranteListado } from './restaurante-listado';

@Service()
export class AnonimoService {
  listadoRestaurantes(): RestauranteListado[] {
    return [
      { id: 1, nombre: 'TeleBurger', tiposComida: ['Americana', 'Basura'] },
      { id: 2, nombre: 'Pizza A Domicilio', tiposComida: ['Italiana', 'Basura'] },
    ];
  }
}
