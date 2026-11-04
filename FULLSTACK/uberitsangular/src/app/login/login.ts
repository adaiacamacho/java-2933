import { Component, inject } from '@angular/core';
import { AnonimoService } from '../anonimo-service';
import { routes } from '../app.routes';
import { Router } from '@angular/router';

@Component({
  imports: [],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {
  private readonly anonimoService = inject(AnonimoService);

  constructor(private router: Router){}

  iniciarSesion(email: string, password: string) {
    console.log(email, password);

    this.anonimoService.login(email, password).then(
      (respuesta) => {console.log(respuesta);
       
        if(sessionStorage.getItem("token")){
          this.router.navigate(['/']);
        }
      }
    );
  }
}
