import { Service } from '@angular/core';
import { RestauranteListado } from './restaurante-listado';

@Service()
export class AnonimoService {
  private readonly URL_RESTAURANTES = 'http://localhost:8080/api/v2/restaurantes';

  async listadoRestaurantes(): Promise<RestauranteListado[]> {
    const respuesta = await fetch(this.URL_RESTAURANTES);
    const restaurantes: RestauranteListado[] = await respuesta.json();

    console.log('listadoRestaurantes()', restaurantes);

    return restaurantes;
  }
}
