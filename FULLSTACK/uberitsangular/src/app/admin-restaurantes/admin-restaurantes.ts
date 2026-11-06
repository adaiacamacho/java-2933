import { Component, inject, signal } from '@angular/core';
import { AnonimoService } from '../anonimo-service';
import { RestauranteListado } from '../restaurante-listado';
import { RouterLink } from '@angular/router';

@Component({
  imports: [RouterLink],
  selector: 'app-admin-restaurantes',
  styleUrl: './admin-restaurantes.css',
  templateUrl: './admin-restaurantes.html',
})
export class AdminRestaurantes {
  private readonly anonimoService = inject(AnonimoService);
  // private readonly changeDetectorRef = inject(ChangeDetectorRef);

  private readonly restaurantes = signal<RestauranteListado[]>([]);

  constructor() {
    this.anonimoService.listadoRestaurantes().then((restaurantes) => {
      this.restaurantes.set(restaurantes);

      // this.changeDetectorRef.markForCheck();
    });
  }
}
