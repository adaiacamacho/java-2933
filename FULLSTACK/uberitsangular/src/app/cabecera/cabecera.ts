import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { JwtService } from '../jwt-service';

@Component({
  imports: [RouterLink],
  selector: 'app-cabecera',
  styleUrl: './cabecera.css',
  templateUrl: './cabecera.html',
})
export class Cabecera {
  private readonly jwtService = inject(JwtService);
}
