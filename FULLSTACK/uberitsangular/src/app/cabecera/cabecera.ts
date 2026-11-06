import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { JwtService } from '../jwt-service';

@Component({
  imports: [RouterLink],
  selector: 'app-cabecera',
  styleUrl: './cabecera.css',
  templateUrl: './cabecera.html',
})
export class Cabecera {
  private readonly jwtService = inject(JwtService);
  private readonly router = inject(Router);

  cerrarSesion() {
    this.jwtService.cerrarSesion();
    this.router.navigateByUrl('/');
  }
}
