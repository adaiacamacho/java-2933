import { ChangeDetectorRef, Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AutenticadoService } from '../autenticado-service';
import { RestauranteCompleto } from '../restaurante-completo';
import { CurrencyPipe } from '@angular/common';

@Component({
  imports: [CurrencyPipe],
  selector: 'app-restaurante-detalle',
  styleUrl: './restaurante-detalle.css',
  templateUrl: './restaurante-detalle.html',
})
export class RestauranteDetalle {
  private readonly autenticadoService = inject(AutenticadoService);
  private readonly route = inject(ActivatedRoute);
  private readonly changeDetectorRef = inject(ChangeDetectorRef);

  restaurante = signal<RestauranteCompleto>({} as RestauranteCompleto);

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.autenticadoService.verRestaurante(id).then((restaurante) => {
      this.restaurante.set(restaurante);
      this.changeDetectorRef.markForCheck();
    });
  }
}
