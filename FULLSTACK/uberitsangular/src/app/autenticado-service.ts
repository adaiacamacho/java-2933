import { Service } from '@angular/core';
import { RestauranteCompleto } from './restaurante-completo';

@Service()
export class AutenticadoService {
  private readonly URL_RESTAURANTES = 'http://localhost:8080/api/v2/restaurantes/';

    async verRestaurante(id: number): Promise<RestauranteCompleto> {
      const respuesta = await fetch(this.URL_RESTAURANTES + id);
      const restaurante: RestauranteCompleto = await respuesta.json();

      console.log('verRestaurante()', id, restaurante);

      return restaurante;
    }
}
