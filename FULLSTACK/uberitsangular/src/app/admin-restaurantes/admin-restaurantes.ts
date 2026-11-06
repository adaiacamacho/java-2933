import { Component, inject, signal } from '@angular/core';
import { AnonimoService } from '../anonimo-service';
import { RestauranteListado } from '../restaurante-listado';

@Component({
  imports: [],
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
      console.log('Principal', restaurantes);

      this.restaurantes.set(restaurantes);

      console.log('Principal', this.restaurantes());

      // this.changeDetectorRef.markForCheck();
    });
  }
}
