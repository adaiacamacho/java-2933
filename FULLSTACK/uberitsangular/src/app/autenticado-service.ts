import { inject, Service } from '@angular/core';
import { RestauranteCompleto } from './restaurante-completo';
import { JwtService } from './jwt-service';

@Service()
export class AutenticadoService {
  private readonly URL_RESTAURANTES = 'http://localhost:8080/api/v2/restaurantes/';

  private readonly jwtService = inject(JwtService);

  async verRestaurante(id: number): Promise<RestauranteCompleto> {
    const respuesta = await fetch(this.URL_RESTAURANTES + id, {
      headers: {
        Authorization: 'Bearer ' + this.jwtService.obtener(),
      },
    });
    const restaurante: RestauranteCompleto = await respuesta.json();

    console.log('verRestaurante()', id, restaurante);

    return restaurante;
  }
}
