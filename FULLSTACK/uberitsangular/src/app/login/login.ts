import { Component, inject } from '@angular/core';
import { AnonimoService } from '../anonimo-service';

@Component({
  imports: [],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {
  private readonly anonimoService = inject(AnonimoService);

  iniciarSesion(email: string, password: string) {
    console.log(email, password);

    this.anonimoService.login(email, password).then((respuesta) => console.log(respuesta));
  }
}
