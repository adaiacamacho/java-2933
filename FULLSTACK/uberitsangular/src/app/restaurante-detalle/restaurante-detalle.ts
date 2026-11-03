import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  imports: [],
  selector: 'app-restaurante-detalle',
  styleUrl: './restaurante-detalle.css',
  templateUrl: './restaurante-detalle.html',
})
export class RestauranteDetalle {
  private readonly route = inject(ActivatedRoute);

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    console.log(id);
  }
}
