import { Service } from '@angular/core';
import { RestauranteListado } from './restaurante-listado';

@Service()
export class AnonimoService {
  private readonly URL_RESTAURANTES = 'http://localhost:8080/api/v2/restaurantes';
  private readonly URL_USUARIOS = 'http://localhost:8080/api/v2/usuarios/';

  async listadoRestaurantes(): Promise<RestauranteListado[]> {
    const respuesta = await fetch(this.URL_RESTAURANTES);
    const restaurantes: RestauranteListado[] = await respuesta.json();

    console.log('listadoRestaurantes()', restaurantes);

    return restaurantes;
  }

  async login(email: string, password: string): Promise<any> {
    const respuesta = await fetch(this.URL_USUARIOS + 'login', {
      method: 'POST',
      body: JSON.stringify({ email, password }),
      headers: {
        'Content-type': 'application/json',
      },
    });

    const datos = await respuesta.json();

    sessionStorage.setItem('token', datos.accessToken);

    return datos;
  }
}
