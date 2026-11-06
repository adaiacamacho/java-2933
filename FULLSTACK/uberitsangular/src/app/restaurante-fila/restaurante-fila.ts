import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RestauranteListado } from '../restaurante-listado';

@Component({
  imports: [RouterLink],
  selector: 'tr[restaurante]',
  styleUrl: './restaurante-fila.css',
  templateUrl: './restaurante-fila.html',
})
export class RestauranteFila {
  public readonly restaurante = input<RestauranteListado>({} as RestauranteListado);
}
