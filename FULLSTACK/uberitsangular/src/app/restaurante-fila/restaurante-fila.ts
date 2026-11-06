import { Component, input, output } from '@angular/core';
import { RestauranteListado } from '../restaurante-listado';

@Component({
  imports: [],
  selector: 'tr[restaurante]',
  styleUrl: './restaurante-fila.css',
  templateUrl: './restaurante-fila.html',
})
export class RestauranteFila {
  public readonly restaurante = input<RestauranteListado>({} as RestauranteListado);
  public readonly clic = output<RestauranteListado>();

  clicRestaurante(restaurante: RestauranteListado) {
    this.clic.emit(restaurante);
  }
}
