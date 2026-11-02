import { Component, inject } from '@angular/core';
import { RestauranteListado } from '../restaurante-listado';
import { AnonimoService } from '../anonimo-service';

@Component({
  imports: [],
  selector: 'app-principal',
  styleUrl: './principal.css',
  templateUrl: './principal.html',
})
export class Principal {
  private readonly anonimoService = inject(AnonimoService);

  restaurantes: RestauranteListado[] = this.anonimoService.listadoRestaurantes();
}
