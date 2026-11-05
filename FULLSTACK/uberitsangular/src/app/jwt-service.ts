
import { computed, Service, signal } from '@angular/core';
import { Token } from './token';

@Service()
export class JwtService {

  private readonly TOKEN = 'token';

  // Estado reactivo del token
  private tokenSignal = signal<string | null>(
    sessionStorage.getItem(this.TOKEN)
  );

  // Estado reactivo de autenticación
  autenticadoSignal = computed(() => {
    const token = this.getToken();

    return token !== null &&
           !!token.exp &&
           token.exp * 1000 > Date.now();
  });

  guardar(token: string): void {
    sessionStorage.setItem(this.TOKEN, token);
    this.tokenSignal.set(token);
  }

  obtener(): string | null {
    return this.tokenSignal();
  }

  getToken(): Token | null {
    const tokenTexto = this.obtener();

    if (!tokenTexto) {
      return null;
    }

    try {
      return JSON.parse(
        atob(tokenTexto.split('.')[1])
      );
    } catch {
      return null;
    }
  }

  getEmail(): string | undefined {
    return this.getToken()?.sub;
  }

  getCaducidad(): Date | undefined {
    const token = this.getToken();

    if (!token) {
      return undefined;
    }

    return new Date(token.exp * 1000);
  }

  autenticado(): boolean {
    return this.autenticadoSignal();
  }

  cerrarSesion(): void {
    sessionStorage.removeItem(this.TOKEN);
    this.tokenSignal.set(null);
  }
}
