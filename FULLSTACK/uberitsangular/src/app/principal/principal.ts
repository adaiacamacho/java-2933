import { Component, inject, signal } from '@angular/core';
import { AnonimoService } from '../anonimo-service';
import { RestauranteFila } from '../restaurante-fila/restaurante-fila';
import { RestauranteListado } from '../restaurante-listado';

@Component({
  imports: [RestauranteFila],
  selector: 'app-principal',
  styleUrl: './principal.css',
  templateUrl: './principal.html',
})
export class Principal {
  private readonly anonimoService = inject(AnonimoService);
  // private readonly changeDetectorRef = inject(ChangeDetectorRef);

  restaurantes = signal<RestauranteListado[]>([]);

  constructor() {
    this.anonimoService.listadoRestaurantes().then((restaurantes) => {
      console.log('Principal', restaurantes);

      this.restaurantes.set(restaurantes);

      console.log('Principal', this.restaurantes());

      // this.changeDetectorRef.markForCheck();
    });
  }
}
